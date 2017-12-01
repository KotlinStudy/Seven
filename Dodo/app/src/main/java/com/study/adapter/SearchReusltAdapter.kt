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
import com.study.bean.SearchBean

/**
 * Created by XIn💕 on 2017/12/1.
 */

class SearchReusltAdapter(context: Context, data :SearchBean.SearchBean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var con: Context? = null
    var data: SearchBean.SearchBean? = null
    var inflater: LayoutInflater? = null
    private var listener : ClickListener?=null
    init {
        this.con = context
        this.data = data
        inflater = LayoutInflater.from(context)
    }
    override fun getItemCount(): Int {
      return  data!!.itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val myHolder = holder as MyHolder
        holder.title.text = data!!.itemList[position].data.title
        Glide.with(con).load(data!!.itemList[position].data.cover.feed).into(holder.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater!!.inflate(R.layout.activity_search_result_item, parent, false)
        val myHolder = MyHolder(view)
        view.setOnClickListener {
            listener?.setOnClick(myHolder.position)
        }
        return myHolder;

    }
    class MyHolder(itemview : View) :RecyclerView.ViewHolder(itemview){
        var image: ImageView =itemView!!.findViewById(R.id.iv_photo) as ImageView
        var title: TextView =itemView!!.findViewById(R.id.tv_title2) as TextView
        var detail: TextView =itemView!!.findViewById(R.id.tv_detail2) as TextView


    }
    interface ClickListener{
        fun setOnClick(position: Int)
    }
    fun setListener(listen : SearchReusltAdapter.ClickListener){
        this.listener=listen
    }
}
