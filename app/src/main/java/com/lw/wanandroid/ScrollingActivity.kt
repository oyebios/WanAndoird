package com.lw.wanandroid

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.lw.mvvm.baseui.BaseActiviy
import com.lw.wanandroid.databinding.ActivityScrollingBinding

class ScrollingActivity : BaseActiviy<ActivityScrollingBinding>() {


    override fun initView(savedInstanceState: Bundle?) {
        setSupportActionBar(findViewById(R.id.toolbar))
        activityBinding?.toolbarLayout?.title = title
        activityBinding?.fab?.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val powerManager = getSystemService(POWER_SERVICE)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_scrolling
    }
}