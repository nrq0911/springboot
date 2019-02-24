package com.thread.a;

public class ThreadPriorityDemo {
	
	public static void main(String[] args) {
		
		
		Thread t1 =  new Thread(new Target());
		Thread t2 =  new Thread(new Target());
		
		
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		
		t1.start();
		t2.start();
		
	}

	public static class Target implements Runnable {
		@Override
		public void run() {
			while(true) {
				System.out.println(Thread.currentThread().getName() + " ...");
			}

		}

	}

}
