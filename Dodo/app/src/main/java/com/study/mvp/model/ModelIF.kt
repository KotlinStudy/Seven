package com.study.mvp.model

import android.content.Context
import com.study.util.FinishListener

/**
 * Created by 张丹阳 on 2017/11/27.
 */
interface ModelIF {
    fun login(ctx : Context,phone : String,pwd : String,listener : FinishListener)
}