package com.thread.b;

public class SignalDemo {
	
	private int signal;
	
	public synchronized void a() {
		while(signal != 0 ) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("a");
		signal ++;
		notifyAll();
	}
	
	public synchronized void b() {
		while(signal != 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("b");
		signal ++;
		notifyAll();
	}
	
	public synchronized void c () {
		while(signal != 2) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("c");
		signal = 0;
		notifyAll();
	}

	static class A implements Runnable {
		private SignalDemo demo;
		public A(SignalDemo demo) {
			this.demo = demo;
		}
		@Override
		public void run() {
			while(true) {
				demo.a();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class B implements Runnable {
		private SignalDemo demo;
		public B(SignalDemo demo) {
			this.demo = demo;
		}
		@Override
		public void run() {
			while(true) {
				demo.b();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class C implements Runnable {
		private SignalDemo demo;
		public C(SignalDemo demo) {
			this.demo = demo;
		}
		@Override
		public void run() {
			while(true) {
				demo.c();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {

		SignalDemo d = new SignalDemo();
		A a = new A(d);
		B b = new B(d);
		C c = new C(d);
		
		new Thread(a).start();
		new Thread(b).start();
		new Thread(c).start();
		
	}



}

