package com.zsc.kot.util



import com.study.bean.Bean
import com.study.bean.FindBean
import com.study.bean.HotData
import io.reactivex.Flowable
import com.study.bean.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/21  18:53
 */
interface ApiService {
    //热门的接口
  //api/v3/ranklist?num=10&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83
  /*//  showapi_appid=42684&showapi_sign=f6527e909abc4edea350ec8b9a9db0f5&num=10
    @GET("197-1?showapi_appid=42684&showapi_sign=f6527e909abc4edea350ec8b9a9db0f5")
    fun getData(@Query("num")num:Int): Flowable<Bean.Bean>*/

    //http://baobab.wandoujia.com/api/v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83

    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getData():Flowable<Bean.Bean>
    @GET("v3/ranklist?num=10&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHotData() :Flowable<HotData.HotData>
    @GET("v3/ranklist?num=10&monthly=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHotDataMonth() :Flowable<HotData.HotData>

    //发现更多
    //FIND_MORE="http://baobab.wandoujia.com/api/
    // v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";
    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getFindData() : Flowable<MutableList<FindBean>>
    //发现频道详情接口
    //"http://baobab.wandoujia.com/api/
    // v3/videos?categoryName=%s&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";
    @GET("v3/videos")
    fun getFindDetailData(@Query("categoryName") categoryName :String,@Query("strategy") strategy :String,
                          @Query("udid") udid :String,@Query("vc") vc :Int) : Flowable<HotData.HotData>

    /**
     * 注册接口
     * https://www.zhaoapi.cn/user/reg
     */
    @GET("reg")
    fun getRegData(@Query("mobile") mobile:String, @Query("password") password:String) : Call<RegBean>
    /**
     * 登录接口
     * https://www.zhaoapi.cn/user/login
     */
    @GET("login")
    fun getLogData(@Query("mobile")mobile:String,@Query("password")pass:String):Call<LogBean>
}