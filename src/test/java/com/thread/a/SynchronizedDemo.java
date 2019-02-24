package com.thread.a;

public class SynchronizedDemo {
	
	private int value;
	
	/**
	 * synchronized 获取 value的值
	 * @return
	 */
	public synchronized int getNext() {
		return value ++;
	}

	public int xx () {
		// monitorenter
		synchronized (SynchronizedDemo.class) {
			
			if(value > 0) {
				return value;
			} else {
				return -1;
			}
			
		}
		// monitorexit
		
	}
	
	public static void main(String[] args) {
		
		SynchronizedDemo s = new SynchronizedDemo();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

}
