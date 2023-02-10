package com.lw.wanandroid.data

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

class UserInfo{
    val user : ObservableField<String> by lazy { ObservableField<String>() }
    val pwd : ObservableField<String> by lazy { ObservableField<String>() }
}