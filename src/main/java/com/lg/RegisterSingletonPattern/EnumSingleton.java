package com.lg.RegisterSingletonPattern;

//注册式单例之枚举单例， effectiveJava推荐单例写法
public enum EnumSingleton {
    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

}
