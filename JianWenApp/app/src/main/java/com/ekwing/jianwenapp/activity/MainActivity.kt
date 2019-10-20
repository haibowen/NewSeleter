package com.ekwing.jianwenapp.activity

import android.app.Dialog
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import com.ekwing.jianwenapp.R
import com.ekwing.jianwenapp.adapter.MyRecyclerViewAdapter
import com.ekwing.jianwenapp.bean.NewData
import com.ekwing.jianwenapp.util.DataUtil
import com.ekwing.jianwenapp.util.HttpClient.Companion.sendOkHttpRequest
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import kotlin.collections.ArrayList
import android.app.AlertDialog as AlertDialog1

class MainActivity : AppCompatActivity() {

    var dataNews = NewData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tool_bar)
        val mode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        getInternetData(DataUtil.TOPURL)
        navListener(mode)
    }

    /**
     * 侧滑栏的点击事件
     */
    private fun navListener(mode: Int) {
        nav_gation.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_first -> {
                    getInternetData(DataUtil.TOPURL)
                    draw_layout.closeDrawers()
                }
                R.id.nav_second -> {
                    getInternetData(DataUtil.SHEHUIURL)
                    draw_layout.closeDrawers()
                }
                R.id.nav_third -> {
                    getInternetData(DataUtil.GUONEIURL)
                    draw_layout.closeDrawers()
                }
                R.id.nav_fourth -> {
                    getInternetData(DataUtil.YULEURL)
                    draw_layout.closeDrawers()
                }
                R.id.nav_fivth -> {
                    getInternetData(DataUtil.TIYUURL)
                    draw_layout.closeDrawers()
                }
                R.id.nav_six -> {
                    getInternetData(DataUtil.JUNSHIURL)
                    draw_layout.closeDrawers()
                }
                R.id.nav_seven -> {
                    getInternetData(DataUtil.KEJIURL)
                    draw_layout.closeDrawers()
                }
                R.id.nav_eight -> {
                    getInternetData(DataUtil.CAIJINGURL)
                    draw_layout.closeDrawers()
                }
                R.id.nav_nine -> {
                    getInternetData(DataUtil.SHISHANGURL)
                    draw_layout.closeDrawers()
                }
                R.id.zhuti -> {
                    if (mode == Configuration.UI_MODE_NIGHT_YES) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    recreate()
                }
                R.id.tuichu -> finish()
            }
            true
        }
    }

    /**
     * RecyclerView的初始化
     */
    private fun initRecyclerView(dAta: NewData) {
        runOnUiThread {
            rv_list.layoutManager = GridLayoutManager(this, 2)
            rv_list.adapter = MyRecyclerViewAdapter(
                this,
                dataNews.result!!.data as ArrayList<NewData.ResultBean.DataBean>
            )
            (rv_list.adapter as MyRecyclerViewAdapter).notifyDataSetChanged()

        }
    }

    /**
     * 请求网络数据
     */
    private fun getInternetData(url: String) {
        sendOkHttpRequest(DataUtil.TOPURL, object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }
            override fun onResponse(call: Call, response: Response) {
                val mGson = Gson()
                dataNews =
                    mGson.fromJson<NewData>(
                        response.body()?.string(),
                        object : TypeToken<NewData>() {
                        }.type
                    )
                initRecyclerView(dataNews)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_right,menu)
       return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dialog : android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        dialog.setMessage(R.string.helpdoc)
        dialog.setNegativeButton("取消",null)
            when (item.itemId){
            R.id.jianjie -> dialog.show()
        }
        return true
    }
}
