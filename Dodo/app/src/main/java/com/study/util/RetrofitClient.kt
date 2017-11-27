package com.study.util

import android.content.Context
import android.util.Log
import android.view.View

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  1. 网络请求的工具类(练习用)
 * 2. @author admin
 * 3. @date 2017/11/23 08:03
 */
class RetrofitClient private constructor(str : String){

    var url=str//请求的baseurl
    var retrofit:Retrofit?=null
    var okhttp : OkHttpClient?=null
    init {
        var inter= HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message-> Log.e("xxx",message)})
        inter.level=HttpLoggingInterceptor.Level.BODY
        okhttp=OkHttpClient.Builder()
                .addInterceptor(inter)
                .connectTimeout(5000,TimeUnit.SECONDS)
                .readTimeout(5000,TimeUnit.SECONDS)
                .build()
        retrofit=Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
    companion object {
        @Volatile
        var client:RetrofitClient?=null
        fun getInstance(str: String) : RetrofitClient{
            if(client==null){
                synchronized(RetrofitClient::class){
                    if(client==null){
                        client= RetrofitClient(str)
                    }
                }
            }
            return client!!
        }
    }
    fun <T> create(service : Class<T>) : T?{
         return retrofit?.create(service)
    }




}