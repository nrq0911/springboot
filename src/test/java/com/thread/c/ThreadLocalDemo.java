package com.thread.c;

public class ThreadLocalDemo {

	private ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		};
	};
	private ThreadLocal<String> string = new InheritableThreadLocal<>();

	public int getNext() {
		Integer value = count.get();
		value++;
		count.set(value);
		return value;
	}

	public static void main(String[] args) throws Exception {
		ThreadLocalDemo d = new ThreadLocalDemo();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					d.string.set("123123");
					d.count.set(200);
					System.out.println(Thread.currentThread().getName() + " " + d.getNext());
					System.out.println(d.count.get() + " " + d.string.get());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName() + " " + d.getNext());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		Thread.sleep(3000);
		System.err.println(d.string.get());
	}

}
