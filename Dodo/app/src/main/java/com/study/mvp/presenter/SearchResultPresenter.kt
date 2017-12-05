package com.study.mvp.presenter

import com.study.base.BasePresenter
import com.study.bean.SearchBean
import com.study.mvp.model.SearchResultModel
import com.study.mvp.view.SearchResultView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by XIn💕 on 2017/12/1.
 */
class SearchResultPresenter: BasePresenter<SearchResultView>() {
    fun getData(querry:String){
        val flowable = SearchResultModel().getSearchData(querry)
        val disposable = flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { data: SearchBean.SearchBean ->getView()?.setSearchData(data)
                }
        add(disposable)
    }
}