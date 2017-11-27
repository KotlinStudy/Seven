package com.study.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/23  8:03
 */
abstract class BaseFragment<V,P:BasePresenter<V>> : Fragment() {

    var fview:View?=null
    var mPresenter:P?=null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         if (fview==null)
         {
             fview=inflater!!.inflate(setLayout(),container,false)
         }
        createPresenter()
        if(mPresenter!=null)
        {
            mPresenter?.attachView(this as V)
        }


        return fview
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        processLogic()
    }


    /**
     * 设置布局
     */
    protected abstract fun setLayout():Int
    /**
     * 创建presenter
     */
    abstract fun createPresenter();
    /**
     * 处理逻辑
     * @return
     */
    protected abstract fun processLogic()

    override fun onDestroy() {
        super.onDestroy()

        mPresenter?.detachView()
    }

    //打日志
    fun LongToast(b:String)= Toast.makeText(context,b, Toast.LENGTH_LONG).show()
    //吐司
    fun LOG(a:String,b:String)= Log.i(a,b)

}