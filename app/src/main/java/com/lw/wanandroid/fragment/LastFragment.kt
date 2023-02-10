package com.lw.wanandroid.fragment

import android.view.View
import com.lw.mvvm.baseui.BaseFragment
import com.lw.wanandroid.R
import com.lw.wanandroid.databinding.FragmentLastBinding

class LastFragment : BaseFragment<FragmentLastBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_last    }

    override fun initView(mRootLayout: View?) {
    }
}