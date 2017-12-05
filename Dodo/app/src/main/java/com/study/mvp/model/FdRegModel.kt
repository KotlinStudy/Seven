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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

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
        /*val regData = RetrofitUtils.intance
                    ?.getInterService(Api.myUrl, ApiService::class.java)
                    ?.getRegData(phone,pwd)
        regData!!.enqueue(object :Callback<RegBean>{
            override fun onResponse(call: Call<RegBean>?, response: Response<RegBean>?) {
                val body = response?.body()
                Toast.makeText(ctx, response!!.code(), Toast.LENGTH_SHORT).show()
                if (body?.code==0){
                    listener.Successful()
                    Toast.makeText(ctx,"注册成功!!!!", Toast.LENGTH_SHORT).show()
                }else{
                    listener.UnSuccessful()
                    Toast.makeText(ctx,"注册失败!!!!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<RegBean>?, t: Throwable?) {
            }
        })*/
    }
}
