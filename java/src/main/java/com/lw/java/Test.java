package com.lw.java;

public class Test {

    public static void main(String[] args) {
        Color blue = new Blue();
        Color red = new Red();
        blue.draw();
        red.draw();
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