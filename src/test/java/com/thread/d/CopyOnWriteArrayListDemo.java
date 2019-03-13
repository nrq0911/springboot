package com.thread.d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.*;

public class CopyOnWriteArrayListDemo {
	
	public static void main(String[] args) throws Exception {

		ArrayList<String> s = new ArrayList<>();
		Collections.synchronizedList(s);

		HashMap<String, Object> res = new HashMap<>();
		Collections.synchronizedMap(res);

		CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

		ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(2);


		threadPool.schedule( () -> System.out.println(1), 1L, TimeUnit.SECONDS);

		CountDownLatch latch = new CountDownLatch(10);
		latch.await();
		latch.countDown();

		CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
		cyclicBarrier.await();
		cyclicBarrier.reset();
		cyclicBarrier.getNumberWaiting();
		cyclicBarrier.isBroken();



	}

}
