package com.thread.tb1;

import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class Demo {
	
	public void target (Thread joinThread) {
		
		System.out.println("target ����ִ����...");
		try {
			joinThread.start();
			joinThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("join �߳�ִ�����...");
		
	}
	
	
	public static void main(String[] args) {
		Demo d = new Demo();
		Thread joinThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					d.a();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				d.target(joinThread);
			}
		}).start();

		Random a = new Random();
		Random b = new Random();
		Random c = new Random();
		Random g = new Random();
		Random e = new Random();
		Random f = new Random();

		System.out.println(a.nextInt());
		System.out.println(b.nextInt());
		System.out.println(c.nextInt());
		System.out.println(g.nextInt());
		System.out.println(e.nextInt());
		System.out.println(f.nextInt());
	}


	protected void a() throws InterruptedException {
		System.out.println("join �߳̽���");
		Thread.sleep(1000);
	}

}
