package com.study.mvp.presenter

import android.util.Log
import com.study.base.BasePresenter
import com.study.bean.Bean
import com.study.mvp.model.FaModel
import com.study.mvp.view.FaView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/23  9:17
 */
class FAPresenter(model:FaModel) :BasePresenter<FaView>() {
    val model=model



    fun loadData()
    {

        model.loadData()?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({data:Bean.Bean->
                    getView()?.setData(data)
                })

    }

 }