package com.lw.mvvm.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationBarView


fun initAndroidxNavigationContorller(fragmentManager: FragmentManager,
                                     @IdRes navHosFragmentId : Int,
                                      navBarViewId : NavigationBarView
){
    val navHosFragment = fragmentManager.findFragmentById(navHosFragmentId) as NavHostFragment
    val navController = navHosFragment.navController
    NavigationUI.setupWithNavController(navBarViewId,navController)
}