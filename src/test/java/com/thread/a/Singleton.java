package com.thread.a;

/**
 * 饿汉模式，很饿很着急，所以类加载时即创建实例对象
 */
public class Singleton {
	
	private Singleton() {}

	private static Singleton instance = new Singleton();
	
	public static Singleton getInstance() {
		return instance;
	}
	
}
