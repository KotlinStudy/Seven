package com.study.bean

/**
 * Created by 张丹阳 on 2017/12/1.
 */
class LogBean {

data class LoginBean(
		val msg: String, //登录成功
		val code: String, //0
		val data: Data
)

data class Data(
		val age: Any, //null
		val appkey: Any, //null
		val appsecret: Any, //null
		val createtime: String, //2017-12-01T16:08:06
		val email: Any, //null
		val fans: Any, //null
		val follow: Any, //null
		val gender: Int, //0
		val icon: Any, //null
		val latitude: Any, //null
		val longitude: Any, //null
		val mobile: String, //13691221153
		val money: Int, //0
		val nickname: Any, //null
		val password: String, //123456
		val praiseNum: Any, //null
		val token: String, //A6165ED742FC3C069BAF7D969DC14981
		val uid: Int, //301
		val userId: Any, //null
		val username: String //13691221153
)
}