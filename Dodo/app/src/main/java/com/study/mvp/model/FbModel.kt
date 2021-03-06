package com.study.mvp.model

import android.content.Context
import com.study.bean.Bean
import com.study.bean.FindBean
import com.study.util.RetrofitUtils
import com.zsc.kot.util.Api
import com.zsc.kot.util.ApiService
import io.reactivex.Flowable

/**
 *类的用途：
 *mac周昇辰
 *2017/11/23  10:59
 */
class FbModel {

    fun loadFind() : Flowable<MutableList<FindBean>>?{
        val findData = RetrofitUtils.intance
                ?.getInterService(Api.baseUrl, ApiService::class.java)
                ?.getFindData()
        return findData
    }
}