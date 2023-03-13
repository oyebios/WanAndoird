package com.lw.wanandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewmodel : ViewModel() {

    fun test() {
        viewModelScope.launch { }
    }
}