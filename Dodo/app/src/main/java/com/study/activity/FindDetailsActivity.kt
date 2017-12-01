package com.study.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.study.R
import com.study.adapter.DetailsAdapter
import com.study.base.BaseActivity
import com.study.bean.HotData
import com.study.bean.Video
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
        val iv = findViewById(R.id.iv) as ImageView
        iv.setOnClickListener(View.OnClickListener {
            this@FindDetailsActivity.finish()
        })
    }
    override fun setDetailsData(detailsBean: HotData.HotData) {

        var adapter=DetailsAdapter(this,detailsBean)
        rlv_FindDetails.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rlv_FindDetails.adapter=adapter
        adapter.setListener(object : DetailsAdapter.ClickListener{
            override fun setOnClick(position: Int) {
                Toast.makeText(this@FindDetailsActivity,"点击了:"+position,Toast.LENGTH_SHORT).show()
                var intent= Intent(this@FindDetailsActivity,WeekItemActivity::class.java)
                var list=detailsBean.itemList[position].data
                var photoUrl : String? = list.cover?.feed
                var category = list.category
                var duration=list.duration
                var title : String? = list.title
                var desc = list.description
                var playUrl = list.playUrl
                var blurred = list.cover?.blurred
                var collect = list.consumption?.collectionCount
                var share = list.consumption?.shareCount
                var reply = list.consumption?.replyCount
                var time = System.currentTimeMillis()
                var videoBean  = Video(photoUrl,title,desc,duration.toLong(),playUrl,category,blurred,collect ,share ,reply,time)
                intent.putExtra("data",videoBean)
                startActivity(intent)
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
