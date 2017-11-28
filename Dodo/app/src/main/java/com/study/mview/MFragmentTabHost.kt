package com.study.mview

/**
 * 类的用途：
 *
 *
 * mac周昇辰
 * 2017/10/30  18:55
 */

import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TabHost
import android.widget.TabWidget

import java.util.ArrayList


class MFragmentTabHost : TabHost, TabHost.OnTabChangeListener {
    private val mTabs = ArrayList<TabInfo>()

    private var mRealTabContent: FrameLayout? = null
    private var mContext: Context? = null
    private var mFragmentManager: FragmentManager? = null
    private var mContainerId: Int = 0
    private var mOnTabChangeListener: TabHost.OnTabChangeListener? = null
    private var mLastTab: MFragmentTabHost.TabInfo? = null
    private var mAttached: Boolean = false

    internal class TabInfo(val tag: String, val clss: Class<*>, val args: Bundle?) {
        var fragment: Fragment? = null
    }

    internal class DummyTabFactory(private val mContext: Context) : TabHost.TabContentFactory {

        override fun createTabContent(tag: String): View {
            val v = View(mContext)
            v.minimumWidth = 0
            v.minimumHeight = 0
            return v
        }
    }

    internal class SavedState : View.BaseSavedState {
        var curTab: String? = null

        constructor(superState: Parcelable) : super(superState) {}

        constructor(`in`: Parcel) : super(`in`) {
            curTab = `in`.readString()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeString(curTab)
        }

        override fun toString(): String {
            return ("FragmentTabHost.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " curTab=" + curTab + "}")
        }

        companion object {

            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(`in`: Parcel): MFragmentTabHost.SavedState {
                    return MFragmentTabHost.SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }

    constructor(context: Context) : super(context, null) {
        initFragmentTabHost(context, null)
    }// Note that we call through to the version that takes an AttributeSet,
    // because the simple Context construct can result in a broken object!

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initFragmentTabHost(context, attrs)
    }

    private fun initFragmentTabHost(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs,
                intArrayOf(android.R.attr.inflatedId), 0, 0)
        mContainerId = a.getResourceId(0, 0)
        a.recycle()

        super.setOnTabChangedListener(this)
    }

    private fun ensureHierarchy(context: Context) {
        // If owner hasn't made its own view hierarchy, then as a convenience
        // we will construct a standard one here.
        if (findViewById(android.R.id.tabs) == null) {
            val ll = LinearLayout(context)
            ll.orientation = LinearLayout.VERTICAL
            addView(ll, FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT))

            val tw = TabWidget(context)
            tw.id = android.R.id.tabs
            tw.orientation = TabWidget.HORIZONTAL
            ll.addView(tw, LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 0f))

            var fl = FrameLayout(context)
            fl.id = android.R.id.tabcontent
            ll.addView(fl, LinearLayout.LayoutParams(0, 0, 0f))

            fl = FrameLayout(context)
            mRealTabContent = fl
            mRealTabContent!!.id = mContainerId
            ll.addView(fl, LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f))
        }
    }


    @Deprecated("Don't call the original TabHost setup, you must instead\n" +
            "      call {@link #setup(Context, FragmentManager)} or\n" +
            "      {@link #setup(Context, FragmentManager, int)}.")
    override fun setup() {
        throw IllegalStateException(
                "Must call setup() that takes a Context and FragmentManager")
    }

    fun setup(context: Context, manager: FragmentManager) {
        ensureHierarchy(context)  // Ensure views required by super.setup()
        super.setup()
        mContext = context
        mFragmentManager = manager
        ensureContent()
    }

    fun setup(context: Context, manager: FragmentManager, containerId: Int) {
        ensureHierarchy(context)  // Ensure views required by super.setup()
        super.setup()
        mContext = context
        mFragmentManager = manager
        mContainerId = containerId
        ensureContent()
        mRealTabContent!!.id = containerId

        // We must have an ID to be able to save/restore our state.  If
        // the owner hasn't set one at this point, we will set it ourselves.
        if (id == View.NO_ID) {
            id = android.R.id.tabhost
        }
    }

    private fun ensureContent() {
        if (mRealTabContent == null) {
            mRealTabContent = findViewById(mContainerId) as FrameLayout
            if (mRealTabContent == null) {
                throw IllegalStateException(
                        "No tab content FrameLayout found for id " + mContainerId)
            }
        }
    }

    override fun setOnTabChangedListener(l: TabHost.OnTabChangeListener) {
        mOnTabChangeListener = l
    }

    fun addTab(tabSpec: TabHost.TabSpec, clss: Class<*>,
               args: Bundle?) {
        tabSpec.setContent(MFragmentTabHost.DummyTabFactory(this!!.mContext!!))

        val tag = tabSpec.tag
        val info = MFragmentTabHost.TabInfo(tag, clss, args)

        if (mAttached) {
            // If we are already attached to the window, then check to make
            // sure this tab's fragment is inactive if it exists.  This shouldn't
            // normally happen.
            info.fragment = mFragmentManager!!.findFragmentByTag(tag)
            if (info.fragment != null && !info.fragment!!.isDetached) {
                val ft = mFragmentManager!!.beginTransaction()
                ft.detach(info.fragment)
                ft.commit()
            }
        }

        mTabs.add(info)
        addTab(tabSpec)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val currentTag = currentTabTag

        // Go through all tabs and make sure their fragments match
        // the correct state.
        var ft: FragmentTransaction? = null
        var i = 0
        val count = mTabs.size
        while (i < count) {
            val tab = mTabs[i]
            tab.fragment = mFragmentManager!!.findFragmentByTag(tab.tag)
            if (tab.fragment != null && !tab.fragment!!.isDetached) {
                if (tab.tag == currentTag) {
                    // The fragment for this tab is already there and
                    // active, and it is what we really want to have
                    // as the current tab.  Nothing to do.
                    mLastTab = tab
                } else {
                    // This fragment was restored in the active state,
                    // but is not the current tab.  Deactivate it.
                    if (ft == null) {
                        ft = mFragmentManager!!.beginTransaction()
                    }
                    ft!!.detach(tab.fragment)
                }
            }
            i++
        }

        // We are now ready to go.  Make sure we are switched to the
        // correct tab.
        mAttached = true
        ft = doTabChanged(currentTag, ft)
        if (ft != null) {
            ft.commit()
            mFragmentManager!!.executePendingTransactions()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mAttached = false
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        val ss = MFragmentTabHost.SavedState(superState)
        ss.curTab = currentTabTag
        return ss
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state !is MFragmentTabHost.SavedState) {
            super.onRestoreInstanceState(state)
            return
        }
        super.onRestoreInstanceState(state.superState)
        setCurrentTabByTag(state.curTab)
    }

    override fun onTabChanged(tabId: String) {
        if (mAttached) {
            val ft = doTabChanged(tabId, null)
            ft?.commit()
        }
        if (mOnTabChangeListener != null) {
            mOnTabChangeListener!!.onTabChanged(tabId)
        }
    }

    private fun doTabChanged(tag: String?,
                             ft: FragmentTransaction?): FragmentTransaction? {
        var ft = ft
        /*  final MFragmentTabHost.TabInfo newTab = getTabInfoForTag(tag);
        if (mLastTab != newTab) {
            if (ft == null) {
                ft = mFragmentManager.beginTransaction();
            }

            if (mLastTab != null) {
                if (mLastTab.fragment != null) {
                    ft.detach(mLastTab.fragment);
                }
            }

            if (newTab != null) {
                if (newTab.fragment == null) {
                    newTab.fragment = Fragment.instantiate(mContext,
                            newTab.clss.getName(), newTab.args);
                    ft.add(mContainerId, newTab.fragment, newTab.tag);
                } else {
                    ft.attach(newTab.fragment);
                }
            }

            mLastTab = newTab;
        }

        return ft;*/
        var newTab: TabInfo? = null
        for (i in mTabs.indices) {
            val tab = mTabs[i]
            if (tab.tag == tag) {
                newTab = tab
            }
        }
        if (newTab == null) {
            throw IllegalStateException("No tab known for tag " + tag!!)
        }
        if (mLastTab != newTab) {
            if (ft == null) {
                ft = mFragmentManager!!.beginTransaction()
            }
            if (mLastTab != null) {
                if (mLastTab!!.fragment != null) {
                    // 将detach替换为hide，隐藏Fragment
                    // ft.detach(mLastTab.fragment);
                    ft!!.hide(mLastTab!!.fragment)
                }
            }
            if (newTab != null) {
                if (newTab.fragment == null) {
                    newTab.fragment = Fragment.instantiate(mContext,
                            newTab.clss.name, newTab.args)
                    ft!!.add(mContainerId, newTab.fragment, newTab.tag)
                } else {
                    // 将attach替换为show，显示Fragment
                    // ft.attach(newTab.fragment);
                    ft!!.show(newTab.fragment)
                }
            }

            mLastTab = newTab
        }
        return ft
    }

    private fun getTabInfoForTag(tabId: String): MFragmentTabHost.TabInfo? {
        var i = 0
        val count = mTabs.size
        while (i < count) {
            val tab = mTabs[i]
            if (tab.tag == tabId) {
                return tab
            }
            i++
        }
        return null
    }
}
