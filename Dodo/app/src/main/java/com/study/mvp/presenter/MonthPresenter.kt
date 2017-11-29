package com.study.mvp.presenter

import com.study.base.BasePresenter
import com.study.bean.HotData
import com.study.mvp.model.MonthMode
import com.study.mvp.view.MonthView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  1. 月排行p
 * 2. @author admin
 * 3. @date 2017/11/23 14:25
 */
class MonthPresenter : BasePresenter<MonthView>() {
    fun getDataMonth(){
        val hotDataMonth = MonthMode().getHotDataMonth()
        val disposable = hotDataMonth.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { data: HotData.HotData -> getView()?.setHotData(data) }
        add(disposable)



    }

}