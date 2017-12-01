package com.study.mvp.presenter

import com.study.base.BasePresenter
import com.study.bean.HotData
import com.study.mvp.model.DetailsModel
import com.study.mvp.view.FindDetailsIf
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by 13435 on 2017/11/29.
 */
class DetailsPresenter(model : DetailsModel) : BasePresenter<FindDetailsIf>() {
    var model = model

    fun loadloadDetails(categoryName: String, strategy: String){
        val disposable = model.loadDetails(categoryName,strategy)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({ detailsBean: HotData.HotData ->
                    getView()?.setDetailsData(detailsBean)
                })
        add(disposable!!)
    }
}