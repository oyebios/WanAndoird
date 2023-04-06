package com.lw.java.concurrence;

import java.util.concurrent.CountDownLatch;

public class MyThread extends Thread {
    CountDownLatch count = new CountDownLatch(0);

    //重写run方法
    @Override
    public void run() {
        System.out.println("线程运行");
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始执行");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
//        myThread.count.countDown();
    }
}


