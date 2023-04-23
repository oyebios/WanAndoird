package com.lw.wanandroid.fragment

import android.view.View
import com.lw.mvvm.baseui.BaseFragment
import com.lw.wanandroid.R
import com.lw.wanandroid.databinding.FragmentLastBinding
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LastFragment : BaseFragment<FragmentLastBinding>(R.layout.fragment_last) {
    override fun initView(mRootLayout: View?) {
        var dispose: Disposable? = null

        Observable.create(ObservableOnSubscribe<String> {

            println("在${Thread.currentThread()}执行")

            it.onNext("test")

        })
            .subscribeOn(Schedulers.newThread())
            .map {
                println("在${Thread.currentThread()}执行map")
                "test2"
            }
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.computation())
            .doOnNext {
                println("在${Thread.currentThread()}执行do")
            }
            .observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
            .doOnNext {
                println("在${Thread.currentThread()}执行do")
            }
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    dispose = d
                    println("在${Thread.currentThread()}订阅")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

                override fun onNext(t: String) {
                    println("在${Thread.currentThread()}监听")
                }

            })


    }
}