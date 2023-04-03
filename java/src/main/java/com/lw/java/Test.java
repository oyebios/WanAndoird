package com.lw.java;

public class Test {
    private static final int MODE_SHIFT = 30;
    private static final int MODE_MASK = 0x3 << MODE_SHIFT;
    public static final int UNSPECIFIED = 0 << MODE_SHIFT;
    public static final int EXACTLY = 1 << MODE_SHIFT;
    public static final int AT_MOST = 2 << MODE_SHIFT;


    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(MODE_SHIFT));
        System.out.println(Integer.toBinaryString(MODE_MASK));
//        System.out.println(Integer.toBinaryString(UNSPECIFIED));
        System.out.println(Integer.toBinaryString(EXACTLY & MODE_MASK));
        System.out.println(Integer.toBinaryString(EXACTLY));
//        System.out.println(Integer.toBinaryString(AT_MOST));
        System.out.println(Integer.toBinaryString(AT_MOST & MODE_MASK));
        System.out.println(Integer.toBinaryString(AT_MOST));
    }
}

class Blue implements Color {

    @Override
    public void draw() {
        System.out.println("blue");
    }
}

class Red implements Color {

    @Override
    public void draw() {
        System.out.println("red");
    }
}


interface Color {
    void draw();
}