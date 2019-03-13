package com.thread.c;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	Random random = new Random();

	public void meeting(CyclicBarrier barrier) {
		try {
			Thread.sleep(random.nextInt(4000));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " 到达会议室等待开会...");

		if(Thread.currentThread().getName().equals("Thread-7")) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			barrier.reset();
		}
		
		try {
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		CyclicBarrierDemo demo = new CyclicBarrierDemo();

		CyclicBarrier barrier = new CyclicBarrier(10, () -> System.err.println("到达会议室，开始开会...") );

		for (int i = 0; i < 20; i++) {
			new Thread(() -> demo.meeting(barrier)).start();
		}
		
		// 查看 barrier 状态
		new Thread( () -> {
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("等待线程 " + barrier.getNumberWaiting());
				System.out.println("is broken " + barrier.isBroken());
			}
		}).start();
	}

}
