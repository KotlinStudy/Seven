package com.study.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.study.R
import com.study.activity.WeekItemActivity
import com.study.adapter.MyAdapter
import com.study.base.BaseFragment
import com.study.bean.Bean
import com.study.bean.Video
import com.study.mvp.model.FaModel
import com.study.mvp.presenter.FAPresenter
import com.study.mvp.view.FaView
import com.study.search.SearchActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragmenta.*


/**
 *类的用途：
 *
 *mac周昇辰
 *2017/11/22  20:22
 */

class FragmentA : BaseFragment<FaView,FAPresenter>(),View.OnClickListener,FaView  {
    override fun setData(bean: Bean.Bean) {

        var list:ArrayList<Bean.Item>?= ArrayList();
        for (be in bean.issueList)
        {
            var itemlist=be.itemList;
            for (item in itemlist)
            {
                if(item.type.equals("video"))
                {
                  list?.add(item )
                }
            }
        }

        var mAdapter:MyAdapter=MyAdapter(context, list!!);
        recycle.adapter=mAdapter
        recycle.layoutManager=LinearLayoutManager(context)
        mAdapter.setItem(object :MyAdapter.MyItemOnClick{
            override fun OnItemClick(position: Int) {

                var intent:Intent= Intent(context, WeekItemActivity::class.java)
                var list =list[position].data
                var photoUrl : String? = list.cover?.feed
                var category = list.category
                var duration=list.duration
                var title : String? = list.title
                var desc = list.description
                var playUrl = list.playUrl
                var blurred = list.cover?.blurred
                var collect = list.consumption?.collectionCount
                var share = list.consumption?.shareCount
                var reply = list.consumption?.replyCount
                var time = System.currentTimeMillis()
                var videoBean  = Video(photoUrl,title,desc,duration.toLong(),playUrl,category,blurred,collect ,share ,reply,time)
                intent.putExtra("data",videoBean)
               // intent.putExtra("bean",Video("111","222"))
                startActivity(intent)
            }
        })
        searchimage.setOnClickListener{
            val intent=Intent()
            intent.setClass(activity,SearchActivity::class.java)
           startActivity(intent)
        }
    }


    override fun onClick(v: View?) {
       when(v?.id)
       {
           R.id.image->{

                   LongToast("xxx");
           }
           else ->{}
       }
    }


    override fun processLogic() {

           mPresenter?.loadData()

    }

    override fun createPresenter() {
       mPresenter= FAPresenter(FaModel());
    }

    override fun setLayout(): Int {
        return R.layout.fragmenta
    }


}






