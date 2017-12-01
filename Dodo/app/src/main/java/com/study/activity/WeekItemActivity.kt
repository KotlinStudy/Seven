package com.study.activity

/**
 * week详情
 */
import android.util.Log
import android.widget.ImageView
import cn.jzvd.JZVideoPlayer
import cn.jzvd.JZVideoPlayerStandard
import com.bumptech.glide.Glide

import com.study.R
import com.study.base.BaseActivity
import com.study.bean.Video
import com.study.mvp.presenter.WeekItemPresenter
import com.study.mvp.view.WeekItemView
import com.study.util.SpUtils
import kotlinx.android.synthetic.main.activity_week_item.*

class WeekItemActivity : BaseActivity<WeekItemView,WeekItemPresenter>() {


    override fun setLayout(): Int {
        return R.layout.activity_week_item
    }

    override fun createPresenter() {
        mPresenter=WeekItemPresenter()
    }
    companion object {
        var list = arrayListOf<Video>()
    }
    override fun processLogic() {
       var video:Video= intent.getSerializableExtra("data") as Video;
        videoplayer.setUp(video.playUrl, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,video.title);
        Glide.with(this).load(video.feed).into(videoplayer.thumbImageView)
        videoplayer.thumbImageView.scaleType=ImageView.ScaleType.FIT_XY
        Glide.with(this).load(video.blurred).into(slt)
        discri.text=video.description
        down.setOnClickListener{
            val get = SpUtils.getInstance(this).get(video.title!!, "");
            if("".equals(get)){
                SpUtils.getInstance(this).put(video.title!!,"1")
                LongToast("开始下载")
                //将下载的任务
                list.add(video)
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
