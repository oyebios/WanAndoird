package com.lw.java.concurrence;

import java.util.concurrent.CountDownLatch;

public class MyThread extends Thread {
    static CountDownLatch count = new CountDownLatch(2);
    int timeSleep = 1000;

    public MyThread(int sec) {
        super();
        timeSleep = sec;
    }

    @Override
    public void run() {

        System.out.println("子线程运行");
        try {
            sleep(timeSleep);
            System.out.println(String.format("%d秒后子线程结束", timeSleep / 1000));
        } catch (InterruptedException e) {

        }
        count.countDown();
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread(1000);
        myThread.start();
        MyThread myThread2 = new MyThread(2000);
        myThread2.start();
        try {
            //主线程等待子线程完成

            System.out.println("主线程等待");
            count.await();
            System.out.println("主线程继续执行");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


