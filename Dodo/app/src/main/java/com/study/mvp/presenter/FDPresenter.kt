package com.study.mvp.presenter

import android.content.Context
import com.study.base.BasePresenter
import com.study.mvp.model.FdModel
import com.study.mvp.view.FaView
import com.study.mvp.view.FdView
import com.study.util.FinishListener

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/23  9:17
 */
class FDPresenter(model : FdModel, fdView : FdView):BasePresenter<FdView>(),FDpresenterIf, FinishListener {

    var model = model
    var fdView = fdView
    override fun userLogin(ctx: Context, phone: String, pwd: String) {
        model.login(ctx,phone,pwd,this)
    }
    override fun Successful() {
        if(fdView != null){
            fdView.setSuccessful()
        }
    }

    override fun UnSuccessful() {
        if(fdView != null){
            fdView.setUnSuccessful()
        }
    }
}