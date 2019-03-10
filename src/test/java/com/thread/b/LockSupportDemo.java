package com.thread.b;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    @Test
    public void test() throws Exception {
        Thread thread =  new Thread( () -> {
            System.out.println("child thread begin park ...");
            //调用park 方法，挂起自己
            LockSupport.park();
            System.out.println("child thread unpark!");
        });

        thread.start();

        Thread.sleep(1000);

        System.out.println("main thread begin unpark!");

        LockSupport.unpark(thread);

    }

    @Test
    public void test1() throws Exception {
        Thread thread =  new Thread( () -> {
            System.out.println("child thread begin park ...");
            //调用park 方法，挂起自己,只有被中断才会推出循环
            while(!Thread.currentThread().isInterrupted()) {
                LockSupport.park();
            }
            System.out.println("child thread unpark!");
        });

        thread.start();

        Thread.sleep(1000);

        System.out.println("main thread begin unpark!");

        thread.interrupt();

    }
}
