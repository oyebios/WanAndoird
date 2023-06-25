package com.lw.bapm.lifecycle.lifecyclecallback


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class AndroidxFragmentLifecycleCallBack : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
    }
}