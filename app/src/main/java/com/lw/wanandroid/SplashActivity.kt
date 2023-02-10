package com.lw.wanandroid

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.MutableLiveData
import com.lw.mvvm.baseui.BaseActiviy

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState, persistentState)
        setContentView( R.layout.activity_main)
    }

}