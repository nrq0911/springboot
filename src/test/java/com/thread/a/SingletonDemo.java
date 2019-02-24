package com.thread.a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonDemo {

    public static void main(String[] args) {

        Singleton2 s1 = Singleton2.getInstance();
        Singleton2 s2 = Singleton2.getInstance();
        Singleton2 s3 = Singleton2.getInstance();
        Singleton2 s4 = Singleton2.getInstance();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);


        ExecutorService threadPool = Executors.newFixedThreadPool(20);

        for(int i = 0;i<20;i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":" + Singleton2.getInstance());
                }
            });
        }

        threadPool.shutdown();

    }

}
