package com.lg.LazySingletonPattern;

//简单懒汉式
public class LazySimpleSingleton {

    private LazySimpleSingleton(){}


    private static LazySimpleSingleton lazy = null;

    //存在线程安全隐患
    public static LazySimpleSingleton getInstance(){

        if(lazy==null){
            lazy = new LazySimpleSingleton();
        }
        return lazy;
    }

}
