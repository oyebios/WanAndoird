package com.lw.bapm.lifecycle.owner

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.util.concurrent.ConcurrentHashMap

interface IStateful {
    fun isActive(): Boolean
}

interface IStateObserver {
    fun on()
    fun off()
}

open class StateWrapper(val observer: IStateObserver, val stateOwner: UIStateOwner) {
    open fun isAttachedTo(owner: LifecycleOwner?) = false
    fun checkLifecycle(lifecycleOwner: LifecycleOwner?) {
        val error = if (lifecycleOwner == null) {
            this is LifecycleStateWrapper
        } else {
            isAttachedTo(lifecycleOwner)
        }
        if (error) {
            throw IllegalStateException("StateWrapper with multi-lifecycleowner  or wrong StateWrapper type")
        }

    }
}

class LifecycleStateWrapper(
    val lifecycleOwner: LifecycleOwner,
    observer: IStateObserver,
    stateOwner: UIStateOwner
) : StateWrapper(observer, stateOwner), LifecycleObserver {
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

interface IStateObservable {
    fun observerForever(iStateObserver: IStateObserver)
    fun observerWithLifecycle(lifecycleOwner: LifecycleOwner, iStateObserver: IStateObserver)
    fun removeObserver(iStateObserver: IStateObserver)
}

enum class State(val dispatch: ((iobserver: IStateObserver) -> Unit)?) {
    INIT(null),
    ON({ iobserver -> iobserver.on() }),
    OFF({ iobserver -> iobserver.off() }),
}

interface IStateOwner : IStateObservable, IStateful

open class UIStateOwner : IStateOwner {
    private val observerMap = ConcurrentHashMap<IStateObserver, StateWrapper>()

    @Volatile
    private var state = State.INIT


    override fun isActive(): Boolean {
        return state == State.ON
    }

    @Synchronized
    override fun observerForever(iStateObserver: IStateObserver) {
        val stateWrapper = observerMap[iStateObserver]
        if (stateWrapper != null) {
            stateWrapper.checkLifecycle(null)
        } else {
            observerMap[iStateObserver] = StateWrapper(iStateObserver, this)
            state.dispatch?.invoke(iStateObserver)
        }

    }

    @Synchronized
    override fun observerWithLifecycle(
        lifecycleOwner: LifecycleOwner,
        iStateObserver: IStateObserver
    ) {
        val stateWrapper = observerMap[iStateObserver]
        if (stateWrapper != null) {
            stateWrapper.checkLifecycle(null)
        } else {
            observerMap[iStateObserver] =
                LifecycleStateWrapper(lifecycleOwner, iStateObserver, this)
            state.dispatch?.invoke(iStateObserver)
        }
    }

    @Synchronized
    override fun removeObserver(iStateObserver: IStateObserver) {
        observerMap.remove(iStateObserver)
    }

    @Synchronized
    fun turnOn() {
        if (state == State.ON) {
            return
        }
        state = State.ON
        dispatchStateChanged()
    }

    @Synchronized
    fun turnOff() {
        if (state == State.OFF) {
            return
        }
        state = State.OFF
        dispatchStateChanged()
    }

    private fun dispatchStateChanged() {
        observerMap.forEach {
            state.dispatch?.invoke(it.key)
        }
    }
}