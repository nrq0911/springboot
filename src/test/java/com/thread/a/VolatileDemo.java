package com.thread.a;

public class VolatileDemo {

	public volatile int a = 1;
	public volatile boolean run = false;

	public synchronized int getA() {
		return a++;
	}

	public synchronized void setA(int a) {
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.a = a;
	}

	public static void main(String[] args) {

		VolatileDemo demo = new VolatileDemo();

		demo.a = 10;

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(demo.a);
			}
		}).start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("======分割线=======" + demo.getA());

		new Thread(() -> {
			for(int i = 1;i<=10;i++) {
				System.err.println("循环 " + i + " 开始");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			demo.run = true;
		}).start();


		new Thread(() -> {
			while(!demo.run) {
				// 自旋
			}
			System.err.println("线程2运行完成...");
		}).start();


	}

}
