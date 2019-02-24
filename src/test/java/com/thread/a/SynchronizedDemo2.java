package com.thread.a;

public class SynchronizedDemo2 {
	
	
	public synchronized void a () {
		System.out.println("a");
		b();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void b() {
		System.out.println("b");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		SynchronizedDemo2 d1= new SynchronizedDemo2();
		SynchronizedDemo2 d2= new SynchronizedDemo2();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				d1.a();
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				d2.b();
			}
		}).start();
	}

}
