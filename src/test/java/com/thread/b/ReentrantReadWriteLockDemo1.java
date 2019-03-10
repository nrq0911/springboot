package com.thread.b;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo1 {

	private Map<String, Object> map = new HashMap<>();

	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	private Lock r = rwl.readLock();
	private Lock w = rwl.writeLock();

	public Object get(String key) {
		r.lock();
		System.out.println(Thread.currentThread().getName() + " get key start ...");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return map.get(key);
		} finally {
			r.unlock();
			System.out.println(Thread.currentThread().getName() + " get key end.");
		}
	}

	public void put(String key, Object value) {
		w.lock();
		System.out.println(Thread.currentThread().getName() + " put key start ...");
		try {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
		} finally {
			w.unlock();
			System.out.println(Thread.currentThread().getName() + " put key end .");
		}
	}

	public static void main(String[] args) {
		ReentrantReadWriteLockDemo1 d = new ReentrantReadWriteLockDemo1();

		new Thread( () -> d.put("key1","value2")).start();
		new Thread( () -> System.err.println(d.get("key1"))).start();
		new Thread( () -> System.err.println(d.get("key1"))).start();
		new Thread( () -> System.err.println(d.get("key1"))).start();
		new Thread( () -> System.err.println(d.get("key1"))).start();
		new Thread( () -> d.put("key1","value3")).start();
		new Thread( () -> System.err.println(d.get("key1"))).start();
		new Thread( () -> System.err.println(d.get("key1"))).start();
		new Thread( () -> System.err.println(d.get("key1"))).start();
		new Thread( () -> System.err.println(d.get("key1"))).start();
		new Thread( () -> d.put("key1","value4")).start();

	}

}
