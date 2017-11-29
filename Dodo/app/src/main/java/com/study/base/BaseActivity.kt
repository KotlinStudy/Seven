package com.study.base

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.study.R

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/22  15:12
 */
abstract  class BaseActivity<V ,P:BasePresenter<V>>: AppCompatActivity() {

    protected var mPresenter:P?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(setLayout())
        //沉浸式状态栏  4.4以上系统
        //通知栏（notification）背景颜色跟随app导航栏（top title）背景颜色变化而变化。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        //创建presenter
         createPresenter()
        //关联
        if(mPresenter!=null)
        {
            mPresenter!!.attachView(this as V)
        }
        processLogic()

    }


    /**
     * 设置布局
     * @return
     */
    protected abstract fun setLayout(): Int
    /**
     * 创建presenter
     */
    abstract fun createPresenter();
    /**
     * 处理逻辑
     * @return
     */
    protected abstract fun processLogic()

    /**
     * 在销毁方法里解绑
     */
    override fun onDestroy() {
        super.onDestroy()
        if(mPresenter!=null)
        {
            mPresenter!!.detachView()
        }
    }

    //打日志
    fun LongToast(b:String)= Toast.makeText(this,b, Toast.LENGTH_LONG).show()
    //吐司
    fun LOG(a:String,b:String)= Log.i(a,b)

}