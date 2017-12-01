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
 * Created by 13435 on 2017/11/30.
 */
class DetailsAdapter(context: Context, data : HotData.HotData) : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {
    var con :Context?=null
    var data: HotData.HotData?=null
    var inflater: LayoutInflater?=null
    private var listener : DetailsAdapter.ClickListener?=null
    init {
        this.con=context
        this.data=data
        inflater= LayoutInflater.from(context)
    }
    override fun onBindViewHolder(holder: DetailsViewHolder?, position: Int) {
        var myHolder=holder as DetailsViewHolder
        holder.title.text=data!!.itemList[position].data.title

        try {
                Glide.with(con).load(data!!.itemList[position].data.cover.feed).into(holder.image)
        } catch (e: Exception) {
            Glide.with(con).load("http://img1.imgtn.bdimg.com/it/u=3081744312,2310390041&fm=27&gp=0.jpg").into(holder.image)
        } finally {
            return
        }
    }

    override fun getItemCount(): Int {
        return data!!.itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DetailsViewHolder {
        var view=inflater!!.inflate(R.layout.hotitem,parent,false)
        var myHolder=DetailsViewHolder(view)
        view.setOnClickListener {
            listener?.setOnClick(myHolder.position)
        }
        return myHolder
    }

    class DetailsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView =itemView!!.findViewById(R.id.slt) as ImageView
        var title: TextView =itemView!!.findViewById(R.id.tv_title) as TextView
    }
    //接口回调点击事件
    interface ClickListener{
        fun setOnClick(position: Int)
    }
    fun setListener(listen : ClickListener){
        this.listener=listen
    }
}