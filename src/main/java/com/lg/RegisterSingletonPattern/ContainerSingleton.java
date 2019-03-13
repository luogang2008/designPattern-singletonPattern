package com.lg.RegisterSingletonPattern;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//注册式单例之容器缓存单例
public class ContainerSingleton {

    private ContainerSingleton(){}

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getBean(String className){

        synchronized (ioc) {
            if(!ioc.containsKey(className)){
                Object object = null;
                try {
                    object = Class.forName(className).newInstance();
                    ioc.put(className, object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return object;
            }else{
                return ioc.get(className);
            }
        }

    }

}
