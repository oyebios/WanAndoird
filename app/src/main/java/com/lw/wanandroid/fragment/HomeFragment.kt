package com.lw.wanandroid.fragment

import android.util.Log
import android.view.View
import com.lw.mvvm.baseui.BaseFragment
import com.lw.mvvm.room.AppDatabase
import com.lw.mvvm.room.User
import com.lw.mvvm.room.createDB
import com.lw.wanandroid.R
import com.lw.wanandroid.data.UserInfo
import com.lw.wanandroid.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(mRootLayout: View?) {
        val user = UserInfo()
//        .setOnClickListener {
////            Navigation.findNavController(it).navigate(R.id.action_page2)
//            user.user.set("第二次")
//
//        }
        fragmentBinding?.user = user
        fragmentBinding?.userInput = user
        user.user.set("初始化")
        GlobalScope.launch {
            val userDb = createDB(activity!!.applicationContext!!,AppDatabase::class.java,"user_info_base")
            userDb.userDao().insertAll(User(user = user.user.get(), password = user.pwd.get(), uid = 1))
            val all = userDb.userDao().getAll()
            all.forEach {
                Log.e("testroom",it.user+"")
            }
        }

    }
}