package com.study.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.R
import com.study.base.BaseFragment
import com.study.mvp.presenter.FAPresenter
import com.study.mvp.presenter.FDPresenter
import com.study.mvp.view.FaView
import com.study.mvp.view.FdView

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/22  20:22
 */
class FragmentD : BaseFragment<FdView,FDPresenter>() {

    override fun processLogic() {

    }

    override fun createPresenter() {
       mPresenter= FDPresenter();
    }

    override fun setLayout(): Int {
        return R.layout.fragmentd
    }


}