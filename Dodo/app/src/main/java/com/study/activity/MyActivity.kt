package com.study.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.study.R
import com.study.bean.Video

class MyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

         //  var bean=intent.getParcelableExtra<Video>("bean")
         //Log.i("xxxx",bean.toString());

    }
}

