package com.study.fragment

import com.study.R
import com.study.base.BaseFragment
import com.study.mvp.presenter.MonthPresenter
import com.study.mvp.view.MonthView

/**
 *  1. 类的用途
 * 2. @author admin
 * 3. @date 2017/11/23 14:26
 */
class MonthFragment : BaseFragment<MonthView,MonthPresenter>(),MonthView {
    override fun setLayout(): Int {
        return R.layout.month
    }

    override fun createPresenter() {
       mPresenter= MonthPresenter()
    }

    override fun processLogic() {

    }
}