package com.study.mvp.presenter

import com.study.base.BasePresenter
import com.study.bean.FindBean
import com.study.mvp.model.FbModel
import com.study.mvp.view.FaView
import com.study.mvp.view.FbView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/23  9:17
 */
class FBPresenter(modelB : FbModel) :BasePresenter<FbView>(){

    var modelB = modelB

    fun loadFind(){
        val disposable = modelB.loadFind()?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ findBean: MutableList<FindBean> ->
                    getView()?.setFindData(findBean)
                })
        add(disposable!!)
    }
}