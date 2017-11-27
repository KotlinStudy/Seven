package com.study.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 *  1. 类的用途
 * 2. @author admin
 * 3. @date 2017/11/24 11:13
 */
class Video(var feed:String?,var title:String?,var description:String?,
            var duration: Long?,var playUrl: String?,var category: String?,
            var blurred : String?,var collect:Int?,var share:Int?,var reply:Int?,var time:Long) :Serializable{

}