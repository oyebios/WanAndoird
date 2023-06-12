package com.lw.bapm

import android.content.Context
import android.util.Log
import java.lang.reflect.Field
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * {@hide}
 */
class ActivityHootUtils(context: Context) {
    private val context: Context

    init {
        this.context = context
    }

    fun hookAms() {

        //一路反射，直到拿到IActivityManager的对象
        try {
            val ActivityManagerNativeClss = Class.forName("android.app.ActivityManagerNative")
            val defaultFiled: Field = ActivityManagerNativeClss.getDeclaredField("gDefault")
            defaultFiled.setAccessible(true)
            val defaultValue: Any = defaultFiled.get(null)
            //反射SingleTon
            val SingletonClass = Class.forName("android.util.Singleton")
            val mInstance: Field = SingletonClass.getDeclaredField("mInstance")
            mInstance.setAccessible(true)
            //到这里已经拿到ActivityManager对象
            val iActivityManagerObject: Any = mInstance.get(defaultValue)


            //开始动态代理，用代理对象替换掉真实的ActivityManager，瞒天过海
            val IActivityManagerIntercept = Class.forName("android.app.IActivityManager")
            val handler = AmsInvocationHandler(iActivityManagerObject)
            val proxy: Any = Proxy.newProxyInstance(
                Thread.currentThread().contextClassLoader,
                arrayOf<Class<*>>(IActivityManagerIntercept),
                handler
            )

            //现在替换掉这个对象
            mInstance.set(defaultValue, proxy)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private inner class AmsInvocationHandler constructor(private val iActivityManagerObject: Any) :
        InvocationHandler {
        override operator fun invoke(proxy: Any?, method: Method, args: Array<Any?>?): Any {

            //我要在这里搞点事情
            if ("startActivity".contains(method.getName())) {
                Log.e("HookUtil", "Activity已经开始启动")
                Log.e("HookUtil", "小弟到此一游！！！")
            }
            return method.invoke(iActivityManagerObject, args)
        }
    }
}

