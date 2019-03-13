package com.lg.hungrySingletonPattern;

public class HungrySingletonV2 {

    //1.私有构造
    private HungrySingletonV2(){}

    //2.提供全局访问点
    private static final HungrySingletonV2 hungrySingleton2;

    static {
        hungrySingleton2 = new HungrySingletonV2();
    }

    public static HungrySingletonV2 getInstance(){
        return hungrySingleton2;
    }


}
