package com.thread;

import org.apache.tomcat.util.net.Nio2Endpoint;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

    public static void main(String[] args) {
        System.out.println("hello world");
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        int nowThreads = topGroup.activeCount();
        Thread[] lstThreads = new Thread[nowThreads];
        topGroup.enumerate(lstThreads);
        for (int i = 0; i < nowThreads; i++) {
            System.out.println("线程number：" + i + " = " + lstThreads[i].getName());
        }

    }

    private Lock lock = new ReentrantLock();
    private int count = 0;


    @Test
    public void lockTest() throws Exception {
        for (int i = 0; i < 100; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    // System.out.println(count);
                    lock.lock();
                    count = count + 1;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            });
            t1.start();
        }

        Thread.sleep(1000);

        System.out.println(count);
    }

    @Test
    public void queue() {
        Nio2Endpoint nio2Endpoint = new Nio2Endpoint();
        Queue<String> queue = new ConcurrentLinkedDeque<>();
        Queue<String> queue2 = new ConcurrentLinkedQueue<>();
        Queue<String> queue3 = new LinkedBlockingDeque<>();
        Queue<String> queue4 = new LinkedBlockingQueue<>();
        Queue<String> queue5 = new ArrayBlockingQueue<>(16);
        Queue<String> queue6 = new PriorityBlockingQueue<>();
        Queue<String> queue7 = new SynchronousQueue<>();
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();

        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(1, 1);
        concurrentHashMap.put(3, 1);
        concurrentHashMap.put(4, 1);
        concurrentHashMap.put(12, 1);
        Iterator<Integer> iterator = concurrentHashMap.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            concurrentHashMap.put(123 ,1);
        }
    }

}
