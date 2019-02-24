package com.thread.a;

public class ThreadWaitNotifyDemo implements Runnable {

	@Override
	public synchronized void run() {
		while(true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("自定义线程执行了 ... ...");
		}
	}
	
	public static void main(String[] args) {
		
		ThreadWaitNotifyDemo n = new ThreadWaitNotifyDemo();

		new Thread(n).start();

		while(true) {
			synchronized (n) {
				System.out.println("主线程执行了 ... ");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				n.notifyAll();
			}
			
		}
		
	}

}
