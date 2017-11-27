package com.study.mvp.model

import android.content.Context
import com.study.bean.Bean
import com.study.util.RetrofitUtils
import com.zsc.kot.util.Api
import com.zsc.kot.util.ApiService
import io.reactivex.Flowable

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/23  10:59
 */
class FaModel {


    fun loadData(): Flowable<Bean.Bean>?
    {
        val data = RetrofitUtils.intance
                ?.getInterService(Api.baseUrl, ApiService::class.java)
                ?.getData()
        return data
    }
}