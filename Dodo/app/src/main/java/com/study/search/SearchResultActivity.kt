package com.study.search

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.study.R
import com.study.activity.WeekItemActivity
import com.study.adapter.SearchReusltAdapter
import com.study.base.BaseActivity
import com.study.bean.SearchBean
import com.study.bean.Video
import com.study.mvp.presenter.SearchResultPresenter
import com.study.mvp.view.SearchResultView
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.week.*

class SearchResultActivity() : BaseActivity<SearchResultView,SearchResultPresenter>(),SearchResultView {
    override fun setSearchData(data: SearchBean.SearchBean) {
      var adapter=SearchReusltAdapter(this,data)
        result_rv.adapter=adapter
        adapter.setListener(object : SearchReusltAdapter.ClickListener{
            override fun setOnClick(position: Int) {
                mIntent(position,data)
            }

        })
    }
    override fun setLayout(): Int {
        return R.layout.activity_search_result
    }
    override fun createPresenter() {
        mPresenter= SearchResultPresenter()
    }

    override fun processLogic() {
        result_rv.layoutManager= LinearLayoutManager(this)
        var querry:String=intent.getStringExtra("search")
        mPresenter!!.getData(querry)
        result_tv.text=querry+"相关"
        result_back.setOnClickListener {
            finish()
        }

    }
    fun mIntent(position: Int,data: SearchBean.SearchBean){
        var intent:Intent=Intent(this,WeekItemActivity::class.java)
        var list=data.itemList[position].data
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


}
