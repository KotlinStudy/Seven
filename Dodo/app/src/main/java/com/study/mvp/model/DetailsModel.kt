package com.study.mvp.model

import android.content.Context
import com.study.bean.HotData
import com.study.util.RetrofitUtils
import com.zsc.kot.util.Api
import com.zsc.kot.util.ApiService
import io.reactivex.Flowable

/**
 * Created by 13435 on 2017/11/29.
 */
class DetailsModel {
    fun loadDetails(categoryName: String, strategy: String?) : Flowable<HotData.HotData>{
        val findDetailData = RetrofitUtils.intance
                ?.getInterService(Api.baseUrl, ApiService::class.java)
                ?.getFindDetailData(categoryName,strategy!!,"26868b32e808498db32fd51fb422d00175e179df",83)
        return findDetailData!!
    }
}