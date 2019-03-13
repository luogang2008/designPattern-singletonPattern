package com.lg.test;

import com.lg.LazySingletonPattern.LazyInnerClassSingleton;
import com.lg.thread.ExectorThread;

public class LazySingletonTest {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new ExectorThread());
        Thread thread2 = new Thread(new ExectorThread());

        System.out.println("-------------------begin---------------");
        thread1.start();
        thread2.start();
        System.out.println("-------------------end---------------");

        LazyInnerClassSingleton instace = LazyInnerClassSingleton.getInstace();
    }
}
