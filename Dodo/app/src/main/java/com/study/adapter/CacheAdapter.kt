package com.study.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.study.R
import com.study.bean.Video

/**
 * Created by 张丹阳 on 2017/11/29.
 */
class CacheAdapter(ctx : Context, list : MutableList<Video>?) : RecyclerView.Adapter<CacheAdapter.ViewHolder>() {
    var context = ctx
    var mList = list

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.cache_title?.text = mList?.get(position)!!.title
        Glide.with(context!!).load(mList?.get(position)?.playUrl!!).into(holder?.cache_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        var view : View = LayoutInflater.from(context).inflate(R.layout.cacheitem,parent,false)
        var viewHolder : ViewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }
    class ViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView){
        var cache_iv = itemView?.findViewById(R.id.cache_iv)as ImageView
        var cache_title = itemView?.findViewById(R.id.cache_title)as TextView
    }
}