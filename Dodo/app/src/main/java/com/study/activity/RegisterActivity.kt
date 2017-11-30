package com.study.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.study.R
import com.study.mvp.model.FdRegModel
import com.study.mvp.presenter.FDRegPresenter
import com.study.mvp.view.FdView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener ,FdView{

    lateinit var fdRegPresenter : FDRegPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val reg_back = findViewById(R.id.reg_back) as ImageView
        val reg_phone = findViewById(R.id.reg_phone) as EditText
        val reg_pwd = findViewById(R.id.reg_pwd) as EditText
        val reg_but = findViewById(R.id.reg_but) as Button

        reg_back.setOnClickListener(this)
        reg_but.setOnClickListener(this)

        fdRegPresenter = FDRegPresenter(FdRegModel(),this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.reg_back->{
                finish()
            }
            R.id.reg_but->{
                val phone = reg_phone.text.toString()
                val pwd = reg_pwd.text.toString()
                fdRegPresenter.userLogin(this,phone, pwd)
            }
        }
    }
    override fun setPhoneError() {
        reg_phone.setError("phone cannot be empty");
    }

    override fun setPwdError() {
        reg_pwd.setError("pwd cannot be empty")
    }
    override fun setSuccessful() {
        var intent = Intent(this,MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent)
    }

    override fun setUnSuccessful() {
        Toast.makeText(this,"注册失败", Toast.LENGTH_SHORT).show()
    }
}
