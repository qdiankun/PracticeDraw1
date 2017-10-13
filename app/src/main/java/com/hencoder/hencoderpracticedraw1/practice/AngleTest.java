package com.hencoder.hencoderpracticedraw1.practice;

/**
 * Created by diankun.qiu on 2017/10/13.
 */

public class AngleTest {


    public static void main(String[] args) {

        double cos = Math.cos(45);
        System.out.println("cos45=" + cos);

        double cos2 = Math.cos(135);
        System.out.println("cos135=" + cos);

        double cos3 = Math.cos(45 * Math.PI / 180);
        System.out.println("cos45=" + cos);

        double cos4 = Math.cos(135 * Math.PI / 180);
        System.out.println("cos135=" + cos);

    }

}
