package com.thread.c;

public class ThreadJoinDemo {

	public void a(Thread joinThread) {

		System.out.println("method a start ...");
		joinThread.start();
		try {
			joinThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method a end ...");

	}

	public void b() {
		System.out.println("method b start ...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method b end ...");
	}

	public static void main(String[] args) {
		ThreadJoinDemo demo = new ThreadJoinDemo();
		Thread joinThread = new Thread(new Runnable() {

			@Override
			public void run() {
				demo.b();
			}
		});

		new Thread(new Runnable() {

			@Override
			public void run() {
				demo.a(joinThread);
			}
		}).start();
	}

}
