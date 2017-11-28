package com.study.mvp.presenter

import com.study.base.BasePresenter
import com.study.bean.HotData
import com.study.mvp.model.WeekMode
import com.study.mvp.view.WeekView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  1. 周排行p层
 * 2. @author admin
 * 3. @date 2017/11/23 14:19
 */
class WeekPresenter : BasePresenter<WeekView>() {
    fun getData(){
        val flowable = WeekMode().getHotData();
        val disposable = flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { data: HotData.HotData ->getView()?.setHotData(data)
                }
        add(disposable)
    }
}