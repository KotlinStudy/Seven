package com.study.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

import java.lang.ref.Reference
import java.lang.ref.WeakReference

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/22  15:27
 */
abstract class BasePresenter<V> {

    private var mRefresh:Reference<V>?=null;
    var compositeDisposable : CompositeDisposable?=null;

    /**
     * 关联的方法
     */
    fun attachView(view: V)
    {
        mRefresh=WeakReference<V>(view);

    }

    /**
     * 获取view
     */

     fun getView():V?=if(isAttach()) mRefresh?.get()else null

    fun isAttach():Boolean{
       return if (mRefresh!=null&& mRefresh!!.get()!=null)true else false
    }
    /**
     * 获取view
     */








    /**
     * 解除关联的方法
     */
     fun detachView()
     {
        if (isAttach())
        { //取消订阅
            remove()
            mRefresh?.clear();
            mRefresh=null;
        }
     }
    /**
     * 添加订阅管理者
     *
     */
    fun add(disposable: Disposable){
        if(compositeDisposable==null)
            compositeDisposable=CompositeDisposable()
        compositeDisposable?.add(disposable)
    }
    /**
     * 取消订阅
     */
    fun remove(){
        if(compositeDisposable!=null){
            compositeDisposable?.dispose()
        }
    }

}