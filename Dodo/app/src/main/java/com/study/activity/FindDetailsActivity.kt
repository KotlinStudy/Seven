package com.study.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.study.R
import com.study.adapter.DetailsAdapter
import com.study.base.BaseActivity
import com.study.bean.HotData
import com.study.mvp.model.DetailsModel
import com.study.mvp.presenter.DetailsPresenter
import com.study.mvp.view.FindDetailsIf
import kotlinx.android.synthetic.main.activity_find_details.*

class FindDetailsActivity : BaseActivity<FindDetailsIf, DetailsPresenter>(),FindDetailsIf {


    lateinit var name: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_details)
        name= intent.getStringExtra("name")
        //初始化组件
        val tv_name = findViewById(R.id.tv_name) as TextView
        tv_name.setText(name)
    }
    override fun setDetailsData(detailsBean: HotData.HotData) {

        var adapter=DetailsAdapter(this,detailsBean)
        rlv_FindDetails.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rlv_FindDetails.adapter=adapter
        adapter.setListener(object : DetailsAdapter.ClickListener{
            override fun setOnClick(position: Int) {
                Toast.makeText(this@FindDetailsActivity,"点击了:"+position,Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun setLayout(): Int {
        return R.layout.activity_find_details
    }

    override fun createPresenter() {
        mPresenter = DetailsPresenter(DetailsModel())
    }

    override fun processLogic() {
        name= intent.getStringExtra("name")
        mPresenter?.loadloadDetails(name,"date")
        Log.i("OOO",name)
    }
}
