package com.lw.wanandroid.fragment

import android.view.View
import com.lw.mvvm.baseui.BaseFragment
import com.lw.wanandroid.R
import com.lw.wanandroid.databinding.FragmentHomeBinding

class SecondFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getLayoutId(): Int {
        return  R.layout.fragment_second
    }

    override fun initView(mRootLayout: View?) {
    }
}