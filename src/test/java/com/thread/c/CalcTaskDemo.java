package com.thread.c;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CalcTaskDemo extends RecursiveTask<Integer> {
	
	private int begin;
	private int end;
	
	public CalcTaskDemo(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}
	
	private static final int threshold = 2;

	@Override
	protected Integer compute() {
		System.out.println(Thread.currentThread().getName() + " 进入 " + begin + " " + end);
		if(end - begin <= threshold) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " 计算完毕..." + begin + " " + end);
			return begin + end;
		}
		
		int middle = (begin + end) / 2;
		
		CalcTaskDemo s = new CalcTaskDemo(begin, middle);
		CalcTaskDemo l = new CalcTaskDemo(middle + 1, end);
		
		s.fork();
		l.fork();
		
		
		int a = s.join();
		int b = l.join();
		System.out.println(Thread.currentThread().getName() + " join " + a + " " + b);
		
		return a + b;
	}
	
	public static void main(String[] args) throws Exception {
		ForkJoinPool pool = new ForkJoinPool(20);
		
		CalcTaskDemo c = new CalcTaskDemo(1, 8);
		Future<Integer> f = pool.submit(c);
		System.out.println(f.get());
		
	}

}
