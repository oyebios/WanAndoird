package com.lw.wanandroid.fragment

import android.view.View
import com.lw.incrementalupdate.PatchUtil
import com.lw.library.filecert.sha256
import com.lw.mvvm.baseui.BaseFragment
import com.lw.wanandroid.R
import com.lw.wanandroid.databinding.FragmentSecondBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondFragment : BaseFragment<FragmentSecondBinding>(R.layout.fragment_second) {
    override fun initView(mRootLayout: View?) {
        fragmentBinding?.oldapkpath = "/sdcard/apk/1.apk"
        fragmentBinding?.newapkpath = "/sdcard/apk/2.apk"
        fragmentBinding?.difffilepath = "/sdcard/apk/diff"
        fragmentBinding?.let { frag ->
            frag.idBtnPatch?.setOnClickListener {
                GlobalScope.launch {
                    PatchUtil().patchAPK(frag.oldapkpath, frag.newapkpath, frag.difffilepath)
                    frag.apkhash = sha256(frag.newapkpath!!)
                }
            }
        }
    }
}