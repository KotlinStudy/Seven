package com.study.util

import android.content.Context
import android.content.SharedPreferences

/**
 *  1. Sharedprefrence工具类
 * 2. @author admin
 * 3. @date 2017/11/28 18:50
 */
class SpUtils private constructor(context: Context,spName:String) {
    private val sp : SharedPreferences
    init {
        sp=context.getSharedPreferences(spName,Context.MODE_PRIVATE)
    }

    //存数据的方法


    //取数据的方法


    companion object {
        //定义一个map集合
        private var map=HashMap<String,SpUtils>()
        /**
         * 获取工具类的实例
         */
//        fun getInstance() :SpUtils{
//
//
//            return null
//        }


    }
}