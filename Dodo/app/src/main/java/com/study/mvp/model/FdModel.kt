package com.study.mvp.model

import android.content.Context
import android.text.TextUtils
import com.study.bean.Bean
import com.study.util.FinishListener
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
class FdModel : ModelIF{
    override fun login(ctx: Context, phone: String, pwd: String, listener: FinishListener) {
        if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)){
            listener.UnSuccessful()
        }else{
            listener.Successful()
        }
    }
}