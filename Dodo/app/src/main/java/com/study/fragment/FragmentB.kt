package com.study.fragment


import android.content.Context
import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.GridLayout
import android.widget.Toast
import com.study.R
import com.study.activity.FindDetailsActivity
import com.study.activity.WeekItemActivity
import com.study.adapter.FindAdapter
import com.study.base.BaseFragment
import com.study.bean.Bean
import com.study.bean.FindBean
import com.study.bean.HotData
import com.study.bean.Video
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
        var adapter = FindAdapter(context,findBean)
        rlv.adapter = adapter
        adapter.setListener(object  : FindAdapter.ClickListener{
            override fun setOnClick(position: Int) {
                var intent : Intent = Intent(activity,FindDetailsActivity::class.java)
                val name = findBean[position].name
                intent.putExtra("name",name)
                startActivity(intent)
                Toast.makeText(activity,"点击了:"+position+","+findBean[position].id,Toast.LENGTH_SHORT).show()
            }
        })

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
