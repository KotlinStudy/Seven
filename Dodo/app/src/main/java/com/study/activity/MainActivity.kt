package com.study.activity

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.study.R
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.study.fragment.FragmentA
import android.widget.LinearLayout
import android.widget.TextView
import com.study.fragment.FragmentB
import com.study.fragment.FragmentC
import com.study.fragment.FragmentD
import com.study.mview.MFragmentTabHost


class MainActivity : AppCompatActivity() {

    private var mTabHost: MFragmentTabHost? = null
    //定义一个布局
    private var layout:LayoutInflater?=null;


    //定义数组来存放Fragment界面
    private val fragmentArray = arrayOf<Class<*>>(FragmentA::class.java, FragmentB::class.java, FragmentC::class.java, FragmentD::class.java)
    //定义数组来存放按钮图片
    private val mImageViewArray = intArrayOf(R.drawable.tab_shouye_but, R.drawable.tab_faxian_but, R.drawable.tab_hot_but,R.drawable.tab_mine_but)
    //Tab选项卡的文字
    private val mTextviewArray = arrayOf("首页", "发现", "热门","我的")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //通知栏（notification）背景颜色跟随app导航栏（top title）背景颜色变化而变化。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
          //开始吧
        initView()
    }

    private fun initView() {

        //实例化布局对象
        layout = LayoutInflater.from(this)
        //实例化TabHost对象，得到TabHost
        mTabHost = findViewById(android.R.id.tabhost) as MFragmentTabHost;
        mTabHost?.setup(this,supportFragmentManager ,R.id.realtabcontent)

        //得到fragment的个数
        val count = fragmentArray.size

        for (i in 0 until count) {
            //为每一个Tab按钮设置图标、文字和内容
            val tabSpec = mTabHost?.newTabSpec(mTextviewArray[i])?.setIndicator(getTabItemView(i))
            //将Tab按钮添加进Tab选项卡中
            if (tabSpec != null) {
                mTabHost?.addTab(tabSpec, fragmentArray[i], null)
            }
            //设置Tab按钮的背景
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.i11);
        }

        //去掉分隔的竖线
        mTabHost?.tabWidget?.showDividers = LinearLayout.SHOW_DIVIDER_NONE
    }


    /**
     * 给Tab按钮设置图标和文字
     */
    private fun getTabItemView(index: Int): View? {
        val view = layoutInflater?.inflate(R.layout.tab_item_view, null)
        val imageView = view?.findViewById(R.id.image) as ImageView
        imageView?.setImageResource(mImageViewArray[index])
        val tab=view?.findViewById(R.id.tab) as TextView
        tab?.text=mTextviewArray[index];
        return view

    }

}
