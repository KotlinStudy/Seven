package com.study.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.R
import com.study.activity.LoginActivity
import com.study.activity.RegisterActivity
import com.study.base.BaseFragment
import com.study.mvp.presenter.FAPresenter
import com.study.mvp.presenter.FDPresenter
import com.study.mvp.view.FaView
import com.study.mvp.view.FdView
import kotlinx.android.synthetic.main.fragmentd.*

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/22  20:22
 */
class FragmentD : BaseFragment<FdView,FDPresenter>(), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.login ->{
                var intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.register ->{
                var intent = Intent(activity, RegisterActivity::class.java)
                startActivity(intent)
            }
           /* R.id.tv_watch ->{
                var intent = Intent(activity,WatchActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_advise ->{
                var intent = Intent(activity,AdviseActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_save ->{
                var intent = Intent(activity,CacheActivity::class.java)
                startActivity(intent)
            }*/
        }
    }

    override fun processLogic() {
        login.setOnClickListener(this)
        register.setOnClickListener(this)

        tv_advise.setOnClickListener(this)
        tv_watch.setOnClickListener(this)
        tv_save.setOnClickListener(this)
    }

    override fun createPresenter() {
    }

    override fun setLayout(): Int {

        return R.layout.fragmentd
    }

}