package com.thread.a;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class MultiThreadDemo {

    public static void main(String[] args) throws Exception {

        // 1. extend Thread class
        A threadA = new A("a");
        threadA.start();

        // 2. implements Runnable interface
        Thread threadB = new Thread(new B());
        threadB.start();

        // 3. nested class
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        }) {
            public void run() {
                System.out.println("sub");
            };
        }.start();

        // 4. implements Callable interface
        C threadC = new C();
        FutureTask<Integer> task = new FutureTask<>(threadC);
        new Thread(task).start();
        System.out.println("线程已经执行。。。");
        Integer result = task.get();
        System.out.println("线程C运行返回值:" + result);


        // 5. Timer class
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer task is run");
            }
        }, 0, 1000);

        //6. threadPool
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        threadPool.shutdown();

        // 7. java 8
        List<Integer> values = Arrays.asList(10,20,30,40);
        int res = new D().add(values);
        System.out.println("多线程运算" + res);


    }


    public static class A extends Thread {

        public A(String name) {
            super(name);
        }

        @Override
        public void run() {
            while(!interrupted()) {
                System.out.println(getName() + "线程开始运行 ... ");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class B implements Runnable {
        @Override
        public void run() {
            System.out.println("thread（runnable way） running ...");

        }
    }

    public static class C implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("thread (callable way) running ....");
            Thread.sleep(3000);
            return 1;
        }
    }

    public  static class D {
        public int add (List<Integer> values) {
            return values.parallelStream().mapToInt( i -> i * 2).sum();
        }
    }


}
