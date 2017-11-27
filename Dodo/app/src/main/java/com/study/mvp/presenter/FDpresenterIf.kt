package com.study.mvp.presenter

import android.content.Context

/**
 * Created by 张丹阳 on 2017/11/27.
 */
interface FDpresenterIf {
    fun userLogin(ctx: Context, phone: String, pwd: String)
}