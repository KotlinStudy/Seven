package com.study.mvp.model

import android.content.Context
import com.study.bean.RegBean
import com.study.util.FinishListener
import io.reactivex.Flowable

/**
 * Created by 张丹阳 on 2017/11/27.
 */
interface ModelIF {
    fun regLog(ctx : Context,phone : String,pwd : String,listener : FinishListener)
}