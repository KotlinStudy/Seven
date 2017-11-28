package com.study.mvp.model

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import com.study.bean.RegBean
import com.study.util.FinishListener
import com.study.util.RetrofitUtils
import com.zsc.kot.util.Api
import com.zsc.kot.util.ApiService
import io.reactivex.Flowable

/**
 * Created by 张丹阳 on 2017/11/28.
 */
class FdRegModel : ModelIF{
    override fun regLog(ctx: Context, phone: String, pwd: String, listener: FinishListener){
        if(TextUtils.isEmpty(phone)){
            listener.onPhoneE()
            Toast.makeText(ctx,"手机号不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if(TextUtils.isEmpty(pwd)){
            listener.onPwdE()
            Toast.makeText(ctx,"密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        listener.Successful()


        fun getRegData() : Flowable<RegBean> {
            val regData = RetrofitUtils.intance
                    ?.getInterService(Api.myUrl, ApiService::class.java)
                    ?.getRegData(phone,pwd)
            return regData!!

        }
    }
}
