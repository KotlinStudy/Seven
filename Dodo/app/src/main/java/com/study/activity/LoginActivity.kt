package com.study.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.study.R
import com.study.mvp.model.FdModel
import com.study.mvp.presenter.FDPresenter
import com.study.mvp.presenter.FDpresenterIf
import com.study.mvp.view.FdView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener,FdView {

    lateinit var fdPresenter : FDpresenterIf
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val log_back = findViewById(R.id.log_back) as ImageView
        val login_but = findViewById(R.id.login_but) as Button
        val log_phone = findViewById(R.id.log_phone) as EditText
        val log_pwd = findViewById(R.id.log_pwd) as EditText
        log_back.setOnClickListener(this)
        login_but.setOnClickListener(this)
        fdPresenter = FDPresenter(FdModel(),this)

    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.log_back->{
                finish()
            }
            R.id.login_but->{
                val phone = log_phone.text.toString()
                val pwd = log_pwd.text.toString()
                fdPresenter.userLogin(this,phone,pwd)
            }
        }
    }

    override fun setPhoneError() {
        Toast.makeText(this,"用户名输入有误", Toast.LENGTH_SHORT).show()
    }

    override fun setPwdError() {
        Toast.makeText(this,"密码输入有误",Toast.LENGTH_SHORT).show()
    }
    override fun setSuccessful() {
        Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show()
    }

    override fun setUnSuccessful() {
        Toast.makeText(this,"失败",Toast.LENGTH_SHORT).show()
    }
}
