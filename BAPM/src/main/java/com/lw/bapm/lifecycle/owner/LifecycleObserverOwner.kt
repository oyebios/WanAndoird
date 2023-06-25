package com.lw.bapm.lifecycle.owner

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.util.concurrent.ConcurrentHashMap


interface ILifecycleObserver {
    fun lifecycleEvent(event: Lifecycle.Event, className: String)
}

open class LifecycleWrapper(
    val observer: ILifecycleObserver,
    val stateOwner: LifecycleObserverOwner
) {
    open fun isAttachedTo(owner: LifecycleOwner?) = false
    fun checkLifecycle(lifecycleOwner: LifecycleOwner?) {
        val error = if (lifecycleOwner == null) {
            this is LifecycleLifecycleWrapper
        } else {
            isAttachedTo(lifecycleOwner)
        }
        if (error) {
            throw IllegalStateException("StateWrapper with multi-lifecycleowner  or wrong StateWrapper type")
        }

    }
}

class LifecycleLifecycleWrapper(
    val lifecycleOwner: LifecycleOwner,
    observer: ILifecycleObserver,
    stateOwner: LifecycleObserverOwner
) : LifecycleWrapper(observer, stateOwner), LifecycleObserver {
    init {
        if (lifecycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
            throw IllegalStateException("DESTROYED lifecycle owner")
        }
    }

    override fun isAttachedTo(owner: LifecycleOwner?): Boolean {
        return lifecycleOwner == owner
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destory() {
        stateOwner.removeObserver(observer)
    }

}

interface ILifecycleObservable {
    fun observerForever(iStateObserver: ILifecycleObserver)
    fun observerWithLifecycle(lifecycleOwner: LifecycleOwner, iStateObserver: ILifecycleObserver)
    fun removeObserver(iStateObserver: ILifecycleObserver)
}

interface ILifecycleOwner : ILifecycleObservable

open class LifecycleObserverOwner : ILifecycleOwner {
    private val observerMap = ConcurrentHashMap<ILifecycleObserver, LifecycleWrapper>()


    @Synchronized
    override fun observerForever(iStateObserver: ILifecycleObserver) {
        val stateWrapper = observerMap[iStateObserver]
        if (stateWrapper != null) {
            stateWrapper.checkLifecycle(null)
        } else {
            observerMap[iStateObserver] = LifecycleWrapper(iStateObserver, this)
        }

    }

    @Synchronized
    override fun observerWithLifecycle(
        lifecycleOwner: LifecycleOwner,
        iStateObserver: ILifecycleObserver
    ) {
        val stateWrapper = observerMap[iStateObserver]
        if (stateWrapper != null) {
            stateWrapper.checkLifecycle(null)
        } else {
            observerMap[iStateObserver] =
                LifecycleLifecycleWrapper(lifecycleOwner, iStateObserver, this)
        }
    }

    @Synchronized
    override fun removeObserver(iStateObserver: ILifecycleObserver) {
        observerMap.remove(iStateObserver)
    }

    @Synchronized
    fun changed(event: Lifecycle.Event, className: String) {
        observerMap.forEach {
            it.key.lifecycleEvent(event, className)
        }
    }
}