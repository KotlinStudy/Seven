package com.study.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.library.flowlayout.FlowLayoutManager
import com.study.R
import com.study.adapter.SearchAdapter

import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragmentd.*
import org.intellij.lang.annotations.JdkConstants

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        seach_back.setOnClickListener {
            finish()
        }
        var data: MutableList<String> = arrayListOf("脱口秀", "城会玩", "666", "笑cry", "漫威",
                "清新", "匠心", "VR", "心理学", "舞蹈", "品牌广告", "粉丝自制", "电影相关", "萝莉", "魔性"
                , "第一视角", "教程", "毕业设计", "奥斯卡", "燃", "冰与火之歌", "温情", "线下campaign", "公益")
        var adapter=SearchAdapter(this,data)
        seach_rv.layoutManager=FlowLayoutManager()
        seach_rv.adapter=adapter
        adapter.setListener(object : SearchAdapter.ClickListener{
            override fun setOnClick(position: Int) {
                mIntent(data.get(position))
            }
            })

        seach.setOnClickListener {
            var ed:String=seach_ed.text.toString().trim()
            Log.i("ssss",ed)
            if (ed.equals(""))
            {
                LongToast("请输入关键字")
            }
            else{
                mIntent(ed)
            }
        }

    }
    fun LongToast(b:String)= Toast.makeText(this,b, Toast.LENGTH_LONG).show()
    fun mIntent(string: String){
        var intent:Intent=Intent(this,SearchResultActivity::class.java)
        intent.putExtra("search",string)
        startActivity(intent)

    }

}
