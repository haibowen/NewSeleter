package com.ekwing.jianwenapp.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ekwing.jianwenapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //跳转传过来的数据
        val title = intent.getStringExtra("title")
        val image = intent.getStringExtra("image")
        val content = intent.getStringExtra("url")
        //图片展示
        Glide.with(this).load(image).into(image_top)
        //标题展示
        coll_too_bar.title = title
        //设置toolbar
        setSupportActionBar(toolbar_top)
        //顶部返回按钮
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        //加载url
        web_view.settings.javaScriptEnabled=false
        web_view.settings.defaultTextEncodingName="utf-8"
        web_view.loadUrl(content)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return true
    }
}
