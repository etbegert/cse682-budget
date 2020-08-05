package com.example.cse682final

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import com.example.cse682final.R
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.tasks.Task


class MainActivity : BasicDrawer() {

    companion object{
        var mContext: Context? = null
    }
    val REQUEST_CHECK_SETTINGS = 0x1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(R.layout.activity_main)
        super.onCreateDrawer()
    }

    override fun onRestart() {
        super.onRestart()
    }

}