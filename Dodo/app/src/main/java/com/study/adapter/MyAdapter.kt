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
import com.study.bean.Bean

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/23  18:19
 */
class MyAdapter(context:Context,list:ArrayList<Bean.Item>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var mContext:Context=context
    var bean=list
    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {

        Glide.with(mContext).load(bean[position].data.cover.feed).into(holder?.image)

        holder?.title?.text=bean[position].data.title

        holder?.text?.text=bean[position].data.category


    }

    override fun getItemCount(): Int {

       return bean.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {

        var view:View=LayoutInflater.from(mContext).inflate(R.layout.item,parent,false)
        var mViewHolder:MyViewHolder=MyViewHolder(view)
        view.setOnClickListener({
            myitem?.OnItemClick(mViewHolder.adapterPosition)

        })
        return mViewHolder
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
    {
        var image=itemView?.findViewById<ImageView>(R.id.iv) as ImageView
        var title=itemView?.findViewById<TextView>(R.id.title) as TextView
        var text=itemView?.findViewById<TextView>(R.id.text) as TextView
    }

    var myitem:MyItemOnClick?=null;
    interface MyItemOnClick
    {
        fun OnItemClick(position:Int)
    }
    fun setItem(item:MyItemOnClick)
    {
        myitem=item
    }
}