package com.ekwing.jianwenapp.util

import okhttp3.OkHttpClient
import okhttp3.Request

class HttpClient {

    companion object{
        fun sendOkHttpRequest( url : String,callback: okhttp3.Callback){
            val okHttpClient=OkHttpClient()
            val request = Request.Builder()
                .url(url)
                .build()
            okHttpClient.newCall(request).enqueue(callback)
        }
    }
}