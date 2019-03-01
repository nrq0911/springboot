package com.thread.d;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolDemo extends RecursiveTask<Integer> {

	private int begin;
	private int end;

	public ForkJoinPoolDemo(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		System.out.println(Thread.currentThread().getName() + " ... ");
		
		int sum = 0;
		// ???????
		if (end - begin <= 2) {
			// ????
			for (int i = begin; i <= end; i++) {
				sum += i;
			}
		} else {
			// ???
			
			ForkJoinPoolDemo d1 = new ForkJoinPoolDemo(begin, (begin + end) / 2);
			ForkJoinPoolDemo d2 = new ForkJoinPoolDemo((begin + end)/2 + 1, end);
			
			// ???????
			d1.fork();
			d2.fork();
			
			Integer a = d1.join();
			Integer b = d2.join();
			
			sum = a + b;
		}

		return sum;
	}

	public static void main(String[] args) throws Exception {

		ForkJoinPool pool = new ForkJoinPool(3);

		Future<Integer> future = pool.submit(new ForkJoinPoolDemo(1, 1000000000));

		System.out.println("....");

		System.out.println("?????????" + future.get());
	}

}
