package com.thread.b;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionSignalDemo {
	
	private int signal;
	
	Lock lock = new ReentrantLock();
	Condition a = lock.newCondition();
	Condition b = lock.newCondition();
	Condition c = lock.newCondition();
	
	
	public void a() {
		lock.lock();
		while(signal != 0 ) {
			try {
				a.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("a");
		signal ++;
		b.signal();
		lock.unlock();
	}
	
	public  void b() {
		lock.lock();
		while(signal != 1) {
			try {
				b.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("b");
		signal ++;
		c.signal();
		lock.unlock();
	}
	
	public  void c () {
		lock.lock();
		while(signal != 2) {
			try {
				c.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("c");
		signal = 0;
		a.signal();
		lock.unlock();
	}
	
	public static void main(String[] args) {
		
		ConditionSignalDemo d = new ConditionSignalDemo();
		A a = new A(d);
		B b = new B(d);
		C c = new C(d);
		
		new Thread(a).start();
		new Thread(b).start();
		new Thread(c).start();
		
	}

	static class A implements Runnable {
		private ConditionSignalDemo ConditionSignalDemo;
		public A(ConditionSignalDemo ConditionSignalDemo) {
			this.ConditionSignalDemo = ConditionSignalDemo;
		}
		@Override
		public void run() {
			while(true) {
				ConditionSignalDemo.a();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	static class B implements Runnable {
		private ConditionSignalDemo ConditionSignalDemo;
		public B(ConditionSignalDemo ConditionSignalDemo) {
			this.ConditionSignalDemo = ConditionSignalDemo;
		}
		@Override
		public void run() {
			while(true) {
				ConditionSignalDemo.b();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	static class C implements Runnable {
		private ConditionSignalDemo ConditionSignalDemo;
		public C(ConditionSignalDemo ConditionSignalDemo) {
			this.ConditionSignalDemo = ConditionSignalDemo;
		}
		@Override
		public void run() {
			while(true) {
				ConditionSignalDemo.c();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}

