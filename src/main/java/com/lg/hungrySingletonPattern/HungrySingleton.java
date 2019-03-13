package com.lg.hungrySingletonPattern;

public class HungrySingleton {

    //1.私有构造
    private HungrySingleton(){}

    //2.提供全局访问点
    private static final HungrySingleton hungrySingleton = new HungrySingleton();


    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }


}
