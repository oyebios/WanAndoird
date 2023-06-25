package com.lw.bapm.lifecycle

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
import androidx.lifecycle.Lifecycle
import com.lw.bapm.lifecycle.owner.*
import java.util.*


object ActivityStateOwner : IStateOwner by ProcessUILifecycleOwner.stateOwner
object ActivityLifecycleOwner : ILifecycleOwner by ProcessUILifecycleOwner.lifecycleOwner

@SuppressLint("PrivateApi")
object ProcessUILifecycleOwner {

    private val activitiesCreated: WeakHashMap<Activity, String> = WeakHashMap()
    private val activitiesStarted: WeakHashMap<Activity, String> = WeakHashMap()
    private val activitiesResumed: WeakHashMap<Activity, String> = WeakHashMap()

    val stateOwner = UIStateOwner()
    val lifecycleOwner = LifecycleObserverOwner()

    internal interface OnSceneChangedListener {
        fun onSceneChangedActivity(newScene: String, origin: String)
    }


    internal var onSceneChangedListener: OnSceneChangedListener? = null
        internal set(value) {
            field = value
            if (value != null && !mStopSent && !TextUtils.isEmpty(recentActivityScene)) {
                value.onSceneChangedActivity(recentActivityScene, "")
            }
        }

    var recentActivityScene = ""
        private set(value) {
            onSceneChangedListener?.apply {
                onSceneChangedActivity(value, field)
            }
            field = value
        }
    var recentFragmentScene: MutableList<String> = mutableListOf()


    private val androidFragmentLifecycleCallBack =
        object : android.app.FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentStarted(
                fm: android.app.FragmentManager,
                f: android.app.Fragment
            ) {
                Log.e("android lifecycle", "onFragmentStarted : ${f.javaClass.simpleName}")
                recentFragmentScene.add(f.javaClass.simpleName)
            }

            override fun onFragmentStopped(
                fm: android.app.FragmentManager,
                f: android.app.Fragment
            ) {
                Log.e("android lifecycle", "onFragmentStopped : ${f.javaClass.simpleName}")
                recentFragmentScene.remove(f.javaClass.simpleName)
            }
        }
    private val androidxFragmentLifecycleCallBack = object : FragmentLifecycleCallbacks() {
        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            Log.e("android lifecycle", "onFragmentStarted : ${f.javaClass.simpleName}")
            recentFragmentScene.add(f.javaClass.simpleName)
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            Log.e("android lifecycle", "onFragmentStopped : ${f.javaClass.simpleName}")
            recentFragmentScene.remove(f.javaClass.simpleName)
        }
    }


    fun init(app: Application) {
        //apptaskmanager init to-do

        attachApp(app)
    }

    private fun attachApp(app: Application) {
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                lifecycleEventChanged(Lifecycle.Event.ON_CREATE, activity.javaClass.name)
                activitiesCreated[activity] = activity.javaClass.name

            }

            override fun onActivityStarted(activity: Activity) {
                lifecycleEventChanged(Lifecycle.Event.ON_START, activity.javaClass.name)
                if (activity is FragmentActivity) {
                    activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                        androidxFragmentLifecycleCallBack,
                        true
                    )
                } else {
                    activity.fragmentManager.registerFragmentLifecycleCallbacks(
                        androidFragmentLifecycleCallBack,
                        true
                    )
                }
                activityStarted(activity)
            }

            override fun onActivityResumed(activity: Activity) {
                lifecycleEventChanged(Lifecycle.Event.ON_RESUME, activity.javaClass.name)
                activityResumed(activity)
            }

            override fun onActivityPaused(activity: Activity) {
                lifecycleEventChanged(Lifecycle.Event.ON_PAUSE, activity.javaClass.name)
                activityPaused(activity)
            }

            override fun onActivityStopped(activity: Activity) {
                lifecycleEventChanged(Lifecycle.Event.ON_STOP, activity.javaClass.name)
                if (activity is FragmentActivity) {
                    activity.supportFragmentManager.unregisterFragmentLifecycleCallbacks(
                        androidxFragmentLifecycleCallBack
                    )
                } else {
                    activity.fragmentManager.unregisterFragmentLifecycleCallbacks(
                        androidFragmentLifecycleCallBack
                    )
                }
                activityStoped(activity)
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                lifecycleEventChanged(Lifecycle.Event.ON_DESTROY, activity.javaClass.name)
                activitiesCreated.remove(activity)
                //可在此处监测activity内存泄漏
            }
        })
    }

    private fun activityStarted(activity: Activity) {
        val isEmptyBefore = activitiesStarted.isEmpty()
        recentActivityScene = activity.javaClass.simpleName
        activitiesStarted[activity] = activity.javaClass.simpleName
        if (isEmptyBefore && mStopSent) {
            //foreground
            dispatchForeground()
            mStopSent = false
        }

    }


    private fun activityResumed(activity: Activity) {
        activitiesResumed[activity] = activity.javaClass.simpleName
    }

    private fun activityPaused(activity: Activity) {
        activitiesResumed.remove(activity)
    }

    /**
     *  当旋转时，activity重新创建，activityStarted数量会为0，但是其实activity还在前台
     *  700ms参考[androidx.lifecycle.ProcessLifecycleOwner]
     */
    private fun activityStoped(activity: Activity) {
        activitiesStarted.remove(activity)
        if (activitiesStarted.isEmpty()) {
            mHandler.postDelayed(mDelayedPauseRunnable, 700)
        }

    }

    private var mStopSent = true

    private var mHandler: Handler = Handler(Looper.getMainLooper())

    private val mDelayedPauseRunnable = Runnable {
        dispatchStopIfNeeded()
    }

    private fun dispatchStopIfNeeded() {
        if (activitiesStarted.isEmpty()) {
            mStopSent = true
            //background
            dispatchBackground()
        }
    }

    private fun lifecycleEventChanged(event: Lifecycle.Event, className: String) {
        lifecycleOwner.changed(event, className)
    }

    private fun dispatchForeground() {
        Log.e("android lifecycle", "dispatchForeground")
        stateOwner.turnOn()
    }

    private fun dispatchBackground() {
        Log.e("android lifecycle", "dispatchBackground")
        stateOwner.turnOff()
    }

}