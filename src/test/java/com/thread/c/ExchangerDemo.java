package com.thread.c;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
	
	public void a (Exchanger<String> exch) {
		
		System.out.println("a thread start ...");
		
		try {
			System.out.println("a thread sleep 2s start.");
			Thread.sleep(2000);
			System.out.println("a thread sleep 2s end.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String res = "12345";
		
		try {
			System.out.println("a thread set res");
			exch.exchange(res);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void b (Exchanger<String> exch) {
		System.out.println("b thread start ...");
		try {
			System.out.println("b thread sleep start. ");
			Thread.sleep(4000);
			System.out.println("b thread sleep end.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = "123456";
		
		try {
			String value = exch.exchange(res);
			System.out.println("b thread change res");
			System.out.println("result: " + value.equals(res));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		ExchangerDemo d = new ExchangerDemo();
		Exchanger<String> exch = new Exchanger<>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				d.a(exch);
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				d.b(exch);
			}
		}).start();
		
	}

}
