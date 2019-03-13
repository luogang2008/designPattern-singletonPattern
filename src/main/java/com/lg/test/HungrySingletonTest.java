package com.lg.test;

import com.lg.hungrySingletonPattern.HungrySingleton;
import com.lg.hungrySingletonPattern.HungrySingletonV2;

public class HungrySingletonTest {

    public static void main(String[] args) {
        HungrySingleton instance = HungrySingleton.getInstance();
        System.out.println(instance);
        instance = HungrySingleton.getInstance();
        System.out.println(instance);
        instance = HungrySingleton.getInstance();
        System.out.println(instance);
        instance = HungrySingleton.getInstance();
        System.out.println(instance);
        instance = HungrySingleton.getInstance();
        System.out.println(instance);

        System.out.println("----------------------------------------------");
        HungrySingletonV2 hungrySingletonV2 = HungrySingletonV2.getInstance();
        System.out.println(hungrySingletonV2);
        hungrySingletonV2 = HungrySingletonV2.getInstance();
        System.out.println(hungrySingletonV2);
        hungrySingletonV2 = HungrySingletonV2.getInstance();
        System.out.println(hungrySingletonV2);
        hungrySingletonV2 = HungrySingletonV2.getInstance();
        System.out.println(hungrySingletonV2);
        hungrySingletonV2 = HungrySingletonV2.getInstance();
        System.out.println(hungrySingletonV2);

    }
}
