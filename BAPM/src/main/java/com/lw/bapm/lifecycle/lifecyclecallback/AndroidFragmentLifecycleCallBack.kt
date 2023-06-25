package com.lw.bapm.lifecycle.lifecyclecallback

import android.app.Fragment
import android.app.FragmentManager


open class AndroidFragmentLifecycleCallBack : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
    }
}

