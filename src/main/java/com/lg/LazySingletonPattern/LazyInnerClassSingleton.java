package com.lg.LazySingletonPattern;

//静态内部类饿汉式
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton(){
        if(LazyHolder.lazy!=null){//防止反射攻击
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    private static class LazyHolder{
        private static final LazyInnerClassSingleton lazy = new LazyInnerClassSingleton();
    }


    private static final LazyInnerClassSingleton getInstace(){
        return LazyHolder.lazy;
    }

    //防止序列化攻击
    private Object readResolve(){
        return LazyHolder.lazy;
    }

}
