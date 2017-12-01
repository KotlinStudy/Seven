package com.study.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.EventLog
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.study.R
import com.study.adapter.CacheAdapter


class CacheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)

        val cache_back = findViewById(R.id.cache_back) as ImageView
        val cache_lv = findViewById(R.id.recy_cache) as RecyclerView
        //返回按钮的点击事件
        cache_back.setOnClickListener(View.OnClickListener {
            this.finish()
        })
    }


}
