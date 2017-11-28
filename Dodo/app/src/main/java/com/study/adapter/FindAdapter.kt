package com.study.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.study.R
import com.study.bean.FindBean
import kotlinx.android.synthetic.main.finditem.view.*
import java.util.zip.Inflater

/**
 * Created by 13435 on 2017/11/24.
 */
class FindAdapter(ctx : Context,list : MutableList<FindBean>?) : RecyclerView.Adapter<FindAdapter.FindViewHolder>() {
    var mContext = ctx
    var mList = list
    private var listener : FindAdapter.ClickListener?=null

    override fun onBindViewHolder(holder: FindViewHolder?, position: Int) {
        holder?.name?.text = mList?.get(position)!!.name
        Glide.with(mContext!!).load(mList?.get(position)?.bgPicture!!).into(holder?.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FindViewHolder? {
        var view : View = LayoutInflater.from(mContext).inflate(R.layout.finditem,parent,false)
        var findViewHolder : FindViewHolder = FindViewHolder(view)
        view.setOnClickListener {
            listener?.setOnClick(findViewHolder.position)
        }
        return findViewHolder
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }
    class FindViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView){
        var name = itemView?.findViewById(R.id.name) as TextView
        var img = itemView?.findViewById(R.id.findIV) as ImageView
    }
    //接口回调点击事件
    interface ClickListener{
        fun setOnClick(position: Int)
    }
    fun setListener(listen : ClickListener){
        this.listener=listen
    }
}