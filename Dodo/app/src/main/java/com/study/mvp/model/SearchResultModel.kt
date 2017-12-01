package com.study.mvp.model

import com.study.bean.SearchBean
import com.study.util.RetrofitClient
import com.zsc.kot.util.Api
import com.zsc.kot.util.ApiService
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by XIn💕 on 2017/12/1.
 */
class SearchResultModel {
    fun getSearchData(querry:String) : Observable<SearchBean.SearchBean> {
        val client = RetrofitClient.getInstance(Api.baseUrl);
        val service = client.create(ApiService::class.java)
        return service!!.getSearchData(10,querry,5)
}}