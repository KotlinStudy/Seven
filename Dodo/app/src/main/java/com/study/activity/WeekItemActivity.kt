package com.study.activity

/**
 * week详情
 */
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.jzvd.JZVideoPlayer
import cn.jzvd.JZVideoPlayerStandard
import com.bumptech.glide.Glide

import com.study.R
import com.study.base.BaseActivity
import com.study.bean.Video
import com.study.mvp.presenter.WeekItemPresenter
import com.study.mvp.view.WeekItemView
import kotlinx.android.synthetic.main.activity_week_item.*

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
            LongToast("开始下载")
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
