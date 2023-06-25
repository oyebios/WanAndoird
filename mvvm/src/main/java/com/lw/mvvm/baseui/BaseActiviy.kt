package com.lw.mvvm.baseui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActiviy<T : ViewDataBinding> : AppCompatActivity() {
    var activityBinding: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("android lifecycle", "${this.javaClass.simpleName}onCreate")
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView<T>(this, setLayoutId())
        initView(savedInstanceState)
    }

    override fun onStart() {
        Log.d("android lifecycle", "${this.javaClass.simpleName}onStart")
        super.onStart()

    }

    override fun onResume() {
        Log.d("android lifecycle", "${this.javaClass.simpleName}onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("android lifecycle", "${this.javaClass.simpleName}onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("android lifecycle", "${this.javaClass.simpleName}onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("android lifecycle", "${this.javaClass.simpleName}onDestroy")
        super.onDestroy()
    }

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun setLayoutId(): Int

}