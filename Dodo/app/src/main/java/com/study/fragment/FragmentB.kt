package com.study.fragment


import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.GridLayout
import com.study.R
import com.study.adapter.FindAdapter
import com.study.base.BaseFragment
import com.study.bean.Bean
import com.study.bean.FindBean
import com.study.mvp.model.FbModel

import com.study.mvp.presenter.FBPresenter
import com.study.mvp.view.FbView
import kotlinx.android.synthetic.main.fragmentb.*

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/22  20:22
 */
class FragmentB : BaseFragment<FbView, FBPresenter>(),FbView {

    override fun setFindData(findBean: MutableList<FindBean>) {

        rlv.layoutManager = GridLayoutManager(activity,2) as RecyclerView.LayoutManager
        rlv.adapter = FindAdapter(context,findBean)
    }


    override fun processLogic() {
        mPresenter?.loadFind()
    }

    override fun createPresenter() {
       mPresenter=FBPresenter(FbModel())
    }

    override fun setLayout(): Int {
        return R.layout.fragmentb
    }

}
