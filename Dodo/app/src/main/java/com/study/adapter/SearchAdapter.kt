package com.study.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.study.R

/**
 * Created by XIn💕 on 2017/11/30.
 */
class SearchAdapter(context: Context, data: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var con: Context? = null
    var data: MutableList<String>? = null
    var inflater: LayoutInflater? = null
    private var listener:ClickListener?=null
    init {
        this.con=context
        this.data=data
        inflater= LayoutInflater.from(context)
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        var view=inflater!!.inflate(R.layout.item_seach,parent,false)
        var myHolder=MyHolder(view)
        view.setOnClickListener{
            listener?.setOnClick(myHolder.position)
            Log.i("sss","66666")
        }
        return myHolder
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        var myHolder=holder as MyHolder
        myHolder.title.text=data!!.get(position)
    }
    override fun getItemCount(): Int {
        return data!!.size
    }
    class MyHolder(iteamView: View) : RecyclerView.ViewHolder(iteamView) {
        var title:TextView =itemView!!.findViewById(R.id.tv_title) as TextView
    }
    interface ClickListener {
        fun setOnClick(position: Int)
    }
    fun setListener(listener: ClickListener){
        this.listener=listener
    }
}