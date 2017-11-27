package com.study.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.R
import com.study.base.BaseFragment
import com.study.mvp.presenter.FAPresenter
import com.study.mvp.presenter.FCPresenter
import com.study.mvp.view.FaView
import com.study.mvp.view.FcView
import kotlinx.android.synthetic.main.fragmentc.*

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/22  20:22
 */
class FragmentC : BaseFragment<FcView,FCPresenter>(),FcView {
    var list = listOf<String>("周排行","月排行")
    var fragmentlist= listOf<Fragment>(WeekFragment(),MonthFragment())
    override fun processLogic() {

        //设置tablayout的模式和值
        tablayout.tabMode=TabLayout.MODE_FIXED
        for(title in list){
            tablayout.addTab(tablayout.newTab().setText(title))
        }
        var adapter =ViewpagerAdapter(activity.supportFragmentManager)
        viewpager.adapter=adapter
        tablayout.setupWithViewPager(viewpager)


    }

    override fun createPresenter() {
       mPresenter= FCPresenter();
    }

    override fun setLayout(): Int {
        return R.layout.fragmentc
    }

    /**
     * 内部类 viewpager适配器
     * inner 修饰内部类
     */
    inner class ViewpagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getPageTitle(position: Int): CharSequence {
            return list.get(position)
        }
        override fun getItem(position: Int): Fragment {
            return fragmentlist.get(position)
        }

        override fun getCount(): Int {
            return fragmentlist.size
        }

    }


}