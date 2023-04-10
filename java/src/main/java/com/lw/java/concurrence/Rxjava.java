package com.lw.java.concurrence;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Rxjava {
    public static void main(String[] args) throws InterruptedException {
        Disposable arrived = Flowable.timer(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("arrived");
                    }
                });
//        System.out.println( Runtime.getRuntime().totalMemory());
//        for (int i= 0;i<100;i++){
//            Flowable.timer(1, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(System.out::println);
//        }
//com.lw.mvvm.CarDao
//        System.out.println( Runtime.getRuntime().totalMemory());
//
//        Thread.sleep(60000);
//
//        System.out.println( Runtime.getRuntime().totalMemory());
    }
}
