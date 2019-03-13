package com.lg.thread;

import com.lg.LazySingletonPattern.DoubleCheckLazySingleton;

public class ExectorThread implements Runnable {
    @Override
    public void run() {
//        LazySimpleSingleton simpleSingleton = LazySimpleSingleton.getInstance();
//        System.out.println(Thread.currentThread().getName() + simpleSingleton);
        DoubleCheckLazySingleton simpleSingleton = DoubleCheckLazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + simpleSingleton);
    }
}
