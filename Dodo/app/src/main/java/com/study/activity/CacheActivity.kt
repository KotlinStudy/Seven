package com.study.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.study.R

class CacheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)

        val cache_lv = findViewById(R.id.recy_cache) as ListView

    }
}
