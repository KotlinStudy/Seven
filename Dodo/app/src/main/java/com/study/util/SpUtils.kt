package com.study.util

import android.content.Context
import android.content.SharedPreferences
import java.util.*

/**
 *  1. Sharedprefrence工具类
 * 2. @author admin
 * 3. @date 2017/11/28 18:50
 */
class SpUtils private constructor(context: Context) {
    private val sp : SharedPreferences

    //初始化
    init {
        sp=context.getSharedPreferences("config",Context.MODE_PRIVATE)
    }

    //存数据的方法
    fun put(key :String,value :Any){
        val edit = sp.edit();
        if(value is String){
            edit.putString(key,value)
        }else if (value is Int){
            edit.putInt(key,value)
        }else if(value is Boolean){
            edit.putBoolean(key,value)
        }
        edit.commit()
    }

    //取数据的方法
    fun get(key:String,delvalue:Any) :Any{
        if(delvalue is String){
            return sp.getString(key,delvalue)
        }else if (delvalue is Int) {
            return sp.getInt(key, delvalue)
        }else if (delvalue is Boolean){
            //delvalue as Boolean
            return sp.getBoolean(key,delvalue)
        }
        return null!!
    }
    //清除数据
    fun remove(key :String){
        sp.edit().remove(key).commit()
    }


    companion object {
        private var spUtil :SpUtils?=null
        /**
         * 获取工具类的实例
         */
        fun getInstance(context: Context) :SpUtils{

            if(spUtil==null){
                synchronized(SpUtils::class.java){
                    if(spUtil==null){
                        spUtil= SpUtils(context)
                    }
                }
            }
            return spUtil!!
        }


    }
}