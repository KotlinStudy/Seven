package com.study.util

import android.util.Log



import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * 类的用途：
 *
 *
 * mac周昇辰 简单封装Retrofit
 * 2017/11/1  11:17
 */

class RetrofitUtils
//私有构造器
private constructor() {


    fun <T> getInterService(url: String, cla: Class<T>): T? {
        val retrofit = getRetrofit(url)
        return retrofit?.create(cla)
    }

    companion object {
        private var retrofitUtils: RetrofitUtils? = null
        //返回本身实例的方法
        //首次判空（节省性能）
        //同步
        //在判空
        val intance: RetrofitUtils?
            get() {
                if (retrofitUtils == null) {
                    synchronized(RetrofitUtils::class.java) {
                        if (retrofitUtils == null) {
                            retrofitUtils = RetrofitUtils()
                        }
                    }
                }
                return this!!.retrofitUtils
            }

        private var retrofit: Retrofit? = null
        @Synchronized
        fun getRetrofit(url: String): Retrofit? {
            val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.i("xxx", message) })
            //设置级别
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(5000, TimeUnit.SECONDS)
                    .readTimeout(5000, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)
                    .build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(url)
                        .client(okHttpClient)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return this!!.retrofit
        }
    }

}
