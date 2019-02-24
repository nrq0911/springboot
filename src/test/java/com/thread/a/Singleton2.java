package com.thread.a;

/**
 * 饱汉模式的双重锁模式，很饱不着急，延迟加载
 * 双重锁模式，是饱汉模式的优化，进行双重判断，当已经创建过实例对象后就无需加锁，提高效率。也是一种推荐使用的方式。
 */
public class Singleton2 {
	
	private Singleton2() {}
	
	private static volatile Singleton2 instance;

	public static Singleton2 getInstance () {
		if(instance == null) {
			synchronized (Singleton2.class) {
				if(instance == null) {
					instance = new Singleton2();
				}
			}
		}
		return instance;
	}

}
