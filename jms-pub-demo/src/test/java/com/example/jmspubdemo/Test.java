package com.example.jmspubdemo;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


public class Test {

    @org.junit.Test
    public void name() {

//        String s1 = "test intern";
        String s1 = "";
        System.out.println(s1.intern());
    }
}
