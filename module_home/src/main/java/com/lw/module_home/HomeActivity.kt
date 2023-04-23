package com.lw.module_home

import android.os.Bundle
import com.lw.module_home.databinding.HomeActivityHomeBinding
import com.lw.mvvm.baseui.BaseActiviy

class HomeActivity : BaseActiviy<HomeActivityHomeBinding>() {
    override fun initView(savedInstanceState: Bundle?) {


    }

    override fun setLayoutId(): Int {
        return R.layout.home_activity_home
    }

}