package com.study.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.study.R
import com.study.activity.WeekItemActivity
import com.study.bean.Video
import com.study.util.ObjectSaveUtils
import com.study.util.SpUtils
import io.reactivex.disposables.Disposable
import zlc.season.rxdownload2.RxDownload
import zlc.season.rxdownload2.entity.DownloadFlag

/**
 * Created by 张丹阳 on 2017/11/29.
 */
class CacheAdapter(context : Context, list: ArrayList<Video>) : RecyclerView.Adapter<CacheAdapter.MyViewHolder>() {
    lateinit var mOnLongLisenter: OnLongClickListener
    var context = context
    var mList = list
    var isDownload = false
    var hasLoaded = false
    lateinit var disposable: Disposable

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        var myHolder = holder as MyViewHolder
        myHolder?.cache_title?.text = mList?.get(position)!!.title
        Glide.with(context!!).load(mList?.get(position)?.feed!!).into(myHolder?.cache_iv)
        var category = mList.get(position)?.category
        val duration = mList.get(position)?.duration
        isDownload = SpUtils.getInstance(context!!).getBoolean(mList?.get(position)?.playUrl!!)
        getDownloadState(mList?.get(position)?.playUrl,myHolder)
        if(isDownload){
            holder?.iv_download_state?.setImageResource(R.drawable.icon_download_stop)
        }else{
            holder?.iv_download_state?.setImageResource(R.drawable.icon_download_start)
        }
        holder?.iv_download_state?.setOnClickListener {
            if (isDownload) {
                isDownload = false
                SpUtils.getInstance(context!!).put(mList?.get(position)?.playUrl!!, false)
                holder?.iv_download_state?.setImageResource(R.drawable.icon_download_start)
                RxDownload.getInstance(context).pauseServiceDownload(mList?.get(position)?.playUrl).subscribe()
            } else {
                isDownload = true
                SpUtils.getInstance(context!!).put(mList?.get(position)?.playUrl!!, true)
                holder?.iv_download_state?.setImageResource(R.drawable.icon_download_stop)
                addMission(mList?.get(position)?.playUrl, position + 1)
            }
        }
        myHolder?.itemView?.setOnClickListener {
            //跳转到视频详情页
            var intent: Intent = Intent(context, WeekItemActivity::class.java)
            var photoUrl: String? = mList?.get(position)?.feed
            var title: String? = mList?.get(position)?.title
            var desc = mList?.get(position)?.description
            var playUrl = mList?.get(position)?.playUrl
            var blurred = mList?.get(position)?.blurred
            var collect = mList?.get(position)?.collect
            var share = mList?.get(position)?.share
            var reply = mList?.get(position)?.reply
            var time = System.currentTimeMillis()
            var videoBean = Video(photoUrl, title, desc, duration, playUrl, category, blurred, collect, share, reply, time)
            //var url = SpUtils.getInstance(context!!).getString(playUrl!!)
            intent.putExtra("data", videoBean)
            if(hasLoaded){
                var files = RxDownload.getInstance(context).getRealFiles(playUrl)
                var uri = Uri.fromFile(files!![0])
                intent.putExtra("data", uri.toString())
                ObjectSaveUtils.saveObject(context,"localFile",videoBean)
            }
            context.startActivity(intent)
        }
        myHolder?.itemView?.setOnLongClickListener {
            mOnLongLisenter.onLongClick(position)
            true
        }
    }
    private fun getDownloadState(playUrl: String?, holder: MyViewHolder?){
        disposable = RxDownload.getInstance(context).receiveDownloadStatus(playUrl).subscribe(){
            event ->
            //当事件为Failed时, 才会有异常信息, 其余时候为null.
            if (event.flag == DownloadFlag.FAILED) {
                val throwable = event.error
                Log.w("Error", throwable)
            }
            var downloadStatus = event.downloadStatus
            var percent = downloadStatus.percentNumber

            Log.e("xxx","lal-"+percent)
            if (percent == 100L) {
                if(!disposable.isDisposed && disposable!= null){
                    disposable.dispose()
                }
                hasLoaded = true
                holder?.iv_download_state?.visibility = View.GONE
                holder?.tv_detail?.text = "已缓存"
                isDownload = false
                SpUtils.getInstance(context!!).put(playUrl.toString(), false)
            } else {
                if (holder?.iv_download_state?.visibility != View.VISIBLE) {
                    holder?.iv_download_state?.visibility = View.VISIBLE
                }
                if (isDownload) {
                    holder?.tv_detail?.text = "缓存中 / $percent%"
                } else {
                    holder?.tv_detail?.text = "已暂停 / $percent%"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder? {
        var view : View = LayoutInflater.from(context).inflate(R.layout.cacheitem,parent,false)
        var myHolder = MyViewHolder(view)
        return myHolder
    }

    override fun getItemCount(): Int {
        return mList?.size
    }

    private fun addMission(playUrl: String?, count: Int) {
        RxDownload.getInstance(context).serviceDownload(playUrl, "download$count").subscribe({
            Toast.makeText(context, "开始下载", Toast.LENGTH_SHORT).show()
        }, {
            Toast.makeText(context, "添加任务失败", Toast.LENGTH_SHORT).show()
        })
    }
    class MyViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView){
        var cache_iv = itemView?.findViewById(R.id.cache_iv)as ImageView
        var cache_title = itemView?.findViewById(R.id.cache_title)as TextView
        var tv_detail: TextView = itemView?.findViewById(R.id.tv_detail) as TextView
        var iv_download_state: ImageView = itemView?.findViewById(R.id.iv_download_state) as ImageView

    }
    interface OnLongClickListener {
        fun onLongClick(position: Int)
    }

    fun setOnLongClickListener(onLongClickListener: OnLongClickListener) {
        mOnLongLisenter = onLongClickListener
    }
}