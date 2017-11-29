package com.study.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.study.R
import com.study.activity.WeekItemActivity
import com.study.adapter.MonthAdapter
import com.study.base.BaseFragment
import com.study.bean.HotData
import com.study.bean.Video
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
        monthAdapter.setListener(object:MonthAdapter.ClickListener{
            override fun setOnClick(position: Int) {
                var intent= Intent(context, WeekItemActivity::class.java)
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