package com.study.fragment

import android.support.v7.widget.LinearLayoutManager
import com.study.R
import com.study.adapter.MonthAdapter
import com.study.base.BaseFragment
import com.study.bean.HotData
import com.study.mvp.presenter.MonthPresenter
import com.study.mvp.view.MonthView
import kotlinx.android.synthetic.main.month.*

/**
 *  1. 类的用途
 * 2. @author admin
 * 3. @date 2017/11/23 14:26
 */
class MonthFragment : BaseFragment<MonthView,MonthPresenter>(),MonthView {
    override fun setHotData(data: HotData.HotData) {
        val monthAdapter = MonthAdapter(activity, data)
        recycler_month.adapter=monthAdapter;
    }

    override fun setLayout(): Int {
        return R.layout.month
    }

    override fun createPresenter() {
       mPresenter= MonthPresenter()
    }

    override fun processLogic() {
        mPresenter?.getDataMonth()
        recycler_month.layoutManager= LinearLayoutManager(context)


    }
}