package com.study.activity

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.study.R
import com.study.adapter.CacheAdapter
import com.study.bean.Video
import com.study.util.ObjectSaveUtils
import com.study.util.SpUtils
import zlc.season.rxdownload2.RxDownload

class CacheActivity : AppCompatActivity() {

    val list = WeekItemActivity.list
    lateinit var mAdapter: CacheAdapter
    var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (list.size > 0) {
                list.clear()
            }
            mAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)

        val cache_back = findViewById(R.id.cache_back) as ImageView
        val cache_lv = findViewById(R.id.recy_cache) as RecyclerView
        DataAsyncTask(mHandler, this).execute()
        //添加布局管理器
        cache_lv.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager
        //返回按钮的点击事件
        cache_back.setOnClickListener(View.OnClickListener {
            this.finish()
        })

        mAdapter = CacheAdapter(this,list)
        mAdapter.setOnLongClickListener(object : CacheAdapter.OnLongClickListener {
            override fun onLongClick(position: Int) {
                addDialog(position)
            }
        })
        cache_lv.adapter = mAdapter
    }
    private fun addDialog(position: Int) {
        var builder = AlertDialog.Builder(this)
        var dialog = builder.create()
        builder.setMessage("是否删除当前视频")
        builder.setNegativeButton("否", {
            dialog, which ->
            dialog.dismiss()
        })
        builder.setPositiveButton("是", {
            dialog, which ->
            deleteDownload(position)
        })
        builder.show()
    }
    private fun deleteDownload(position: Int) {
        RxDownload.getInstance(this).deleteServiceDownload(list[position].playUrl, true).subscribe()
        SpUtils.getInstance(this).put(list[position].playUrl.toString(), "")
        var count = position + 1
        ObjectSaveUtils.deleteFile("download$count", this)
        list.removeAt(position)
        mAdapter.notifyItemRemoved(position)
    }
    private class DataAsyncTask(handler: Handler, activity: CacheActivity) : AsyncTask<Void, Void, ArrayList<Video>>() {
        var activity: CacheActivity = activity
        var handler = handler
        override fun doInBackground(vararg params: Void?): ArrayList<Video>? {
            var list = ArrayList<Video>()
            var count: Int = SpUtils.getInstance(activity).getInt("count")
            var i = 1
            while (i.compareTo(count) <= 0) {
                var bean: Video
                if (ObjectSaveUtils.getValue(activity, "download$i") == null) {
                    continue
                } else {
                    bean = ObjectSaveUtils.getValue(activity, "download$i") as Video
                }
                list.add(bean)
                i++
            }
            return list
        }
    }
}
