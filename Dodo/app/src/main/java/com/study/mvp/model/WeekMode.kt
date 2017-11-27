package com.study.mvp.model

import android.util.Log
import com.study.bean.HotData
import com.study.util.RetrofitClient
import com.study.util.RetrofitUtils
import com.zsc.kot.util.Api
import com.zsc.kot.util.ApiService
import io.reactivex.Flowable

/**
 *  1. 类的用途
 * 2. @author admin
 * 3. @date 2017/11/23 16:21
 */
class WeekMode {
    fun getHotData() : Flowable<HotData.HotData>{
        val client = RetrofitClient.getInstance(Api.baseUrl);
        val service = client.create(ApiService::class.java)
       //val service = RetrofitUtils.intance?.getInterService(Api.baseUrl, ApiService::class.java);
        return service!!.getHotData()
    }
}