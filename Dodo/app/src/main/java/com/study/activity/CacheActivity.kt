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
import com.study.bean.Video
import com.study.bean.VideoMap
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CacheActivity : AppCompatActivity() {

    val num : MutableList<Video>? = null
    //订阅方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(messageEvent: Video){
        Log.e("ddd","213")
       // num?.add(messageEvent)
        Log.e("ddd",messageEvent.title)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)
        //注册EventBus
        EventBus.getDefault().register(this)

        val cache_back = findViewById(R.id.cache_back) as ImageView
        val cache_lv = findViewById(R.id.recy_cache) as RecyclerView
        //返回按钮的点击事件
        cache_back.setOnClickListener(View.OnClickListener {
            this.finish()
        })

        var cacheAdapter = CacheAdapter(this,num)
        cache_lv.adapter = cacheAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}
