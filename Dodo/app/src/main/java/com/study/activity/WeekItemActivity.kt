package com.study.activity

/**
 * week详情
 */
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.TextUtils
import android.util.Log
import cn.jzvd.JZVideoPlayer
import cn.jzvd.JZVideoPlayerStandard
import com.bumptech.glide.Glide

import com.study.R
import com.study.base.BaseActivity
import com.study.bean.Video
import com.study.bean.VideoMap
import com.study.mvp.presenter.WeekItemPresenter
import com.study.mvp.view.WeekItemView
import com.study.util.SpUtils
import kotlinx.android.synthetic.main.activity_week_item.*
import org.greenrobot.eventbus.EventBus

class WeekItemActivity : BaseActivity<WeekItemView,WeekItemPresenter>() {

    override fun setLayout(): Int {
        return R.layout.activity_week_item
    }

    override fun createPresenter() {
        mPresenter=WeekItemPresenter()
    }

    override fun processLogic() {
       var video:Video= intent.getSerializableExtra("data") as Video;
        //LongToast(video.title!!)
        videoplayer.setUp(video.playUrl, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,video.title);
        Glide.with(this).load(video.feed).into(videoplayer.thumbImageView)
        Glide.with(this).load(video.blurred).into(slt)
        discri.text=video.description
        down.setOnClickListener{
            val get = SpUtils.getInstance(this).get(video.title!!, "");
            if("".equals(get)){
                SpUtils.getInstance(this).put(video.title!!,"1")
                LongToast("开始下载")
                //将下载的任务
                EventBus.getDefault().post(video)
            }else{
                LongToast("以加入下载队列")
            }
        }
    }

    override fun onBackPressed() {
        if(JZVideoPlayer.backPress()){
            return
        }
        super.onBackPressed();
    }

    override fun onPause() {
        super.onPause()
        JZVideoPlayer.releaseAllVideos();
    }


}
