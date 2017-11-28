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
import com.study.bean.HotData

/**
 * Created by XIn💕 on 2017/11/27.
 */
class MonthAdapter(context: Context, data: HotData.HotData) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var con: Context? = null
    var data: HotData.HotData? = null
    var inflater: LayoutInflater? = null
    private var listener: MonthAdapter.ClickListener? = null

    init {
        this.con = context
        this.data = data
        inflater = LayoutInflater.from(context)
    }

    override fun getItemCount(): Int {
        return data!!.itemList.size

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater!!.inflate(R.layout.hotitem, parent, false)
        val myHolder = MyHolder(view)
        view.setOnClickListener {
            listener?.setOnClick(myHolder.position)
        }
        return myHolder;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val myHolder = holder as MyHolder
        holder.title.text = data!!.itemList[position].data.title
        Glide.with(con).load(data!!.itemList[position].data.cover.detail).into(holder.image)
    }

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var image: ImageView = itemView!!.findViewById(R.id.slt) as ImageView
        var title: TextView = itemView!!.findViewById(R.id.tv_title) as TextView;
    }
    //接口回调点击事件
    interface ClickListener{
        fun setOnClick(position: Int)
    }
    fun setListener(listen : ClickListener){
        this.listener=listen
    }
}