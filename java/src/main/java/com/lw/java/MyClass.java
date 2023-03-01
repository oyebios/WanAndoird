package com.lw.java;


public class MyClass {
    public static void main(String[] args) {
        final MyClass a = new MyClass();
        final MyClass b = new MyClass();

        final sum v = new sum();
        v.sum = 1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    a.setData(v);

                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    b.setData(v);
                }
            }
        }).start();
    }

    synchronized void setData(sum a) {
//        synchronized (this){
        a.sum += 1;
        System.out.println("存储" + a.sum);

//        }
    }
}

class sum {
    public int sum;
}