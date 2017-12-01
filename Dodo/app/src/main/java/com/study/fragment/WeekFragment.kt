package com.study.fragment

import android.content.Intent
import android.os.Handler
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import com.study.R
import com.study.activity.WeekItemActivity
import com.study.adapter.WeekAdapter
import com.study.base.BaseFragment
import com.study.bean.HotData
import com.study.bean.Video
import com.study.mvp.presenter.WeekPresenter
import com.study.mvp.view.WeekView
import kotlinx.android.synthetic.main.month.*
import kotlinx.android.synthetic.main.week.*
import java.util.*

/**
 *  1. 周排行
 * 2. @author admin
 * 3. @date 2017/11/23 14:13
 */
class WeekFragment : BaseFragment<WeekView,WeekPresenter>(),WeekView{

    var handler:Handler= Handler()
    override fun setHotData(data: HotData.HotData) {
        var adapter=WeekAdapter(activity,data)
            recycler.adapter=adapter
        adapter.setListener(object : WeekAdapter.ClickListener{
            override fun setOnClick(position: Int) {
                var intent=Intent(context,WeekItemActivity::class.java)
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
                activity.startActivity(intent)
            }

        })
    }

    override fun setLayout(): Int {
        return R.layout.week
    }

    override fun createPresenter() {
       mPresenter= WeekPresenter()
    }

    override fun processLogic() {
        mPresenter?.getData()
        recycler.layoutManager=LinearLayoutManager(context)
        refresh.setOnRefreshListener {
            handler.postDelayed(Runnable {
                refresh.isRefreshing=false
            },2000)
        }
    }





}