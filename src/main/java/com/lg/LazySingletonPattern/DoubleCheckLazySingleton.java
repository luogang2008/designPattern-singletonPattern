package com.lg.LazySingletonPattern;

//双重校验锁懒汉式
public class DoubleCheckLazySingleton {

    private DoubleCheckLazySingleton(){}


    private volatile static DoubleCheckLazySingleton lazy = null;

    public static DoubleCheckLazySingleton getInstance(){

        if(lazy==null){
            synchronized (DoubleCheckLazySingleton.class){
                if(lazy==null){
                    lazy = new DoubleCheckLazySingleton();
                }
            }
        }
        return lazy;
    }

}
