package com.study.mvp.presenter

import android.content.Context
import com.study.base.BasePresenter
import com.study.mvp.model.FdRegModel
import com.study.mvp.view.FdView
import com.study.util.FinishListener

/**
 * Created by 张丹阳 on 2017/11/28.
 */
class FDRegPresenter(regModel: FdRegModel, fdView: FdView): BasePresenter<FdView>(),FDpresenterIf, FinishListener {

    var regModel = regModel
    var fdView = fdView
    override fun userLogin(ctx: Context, phone: String, pwd: String) {
        regModel.regLog(ctx,phone,pwd,this)
    }
    override fun onPhoneE() {
        if(fdView != null){
            fdView.setPhoneError()
        }
    }

    override fun onPwdE() {
        if(fdView != null){
            fdView.setPwdError()
        }
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