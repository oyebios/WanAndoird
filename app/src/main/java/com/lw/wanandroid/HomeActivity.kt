package com.lw.wanandroid

import android.os.Bundle
import com.lw.mvvm.baseui.BaseActiviy
import com.lw.mvvm.navigation.initAndroidxNavigationContorller
import com.lw.wanandroid.data.UserInfo
import com.lw.wanandroid.databinding.ActivityMainBinding

class HomeActivity : BaseActiviy<ActivityMainBinding>() {


    override fun initView(savedInstanceState: Bundle?) {
        //初始化AndroidX navigation导航
        initAndroidxNavigationContorller(supportFragmentManager,R.id.id_fragment_container,findViewById(R.id.id_bottom_navigation))
        val user = UserInfo()
        activityBinding?.user = user
        user.user.set("初始化")
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

}