package com.thread.d;

public class BeforeHappenDemo {
	
	private int a ;
	private int b;
	private int c;
	
	public void a () {

		// 写后读
		// 读后写
		// 写后写

		b = 2;

		a = 1; // 写操作
		c = a; // 读操作
		b = c + a;
		System.out.println(b);
	}
	
	public static void main(String[] args) {
		new BeforeHappenDemo().a();
	}

	private int d;
	private boolean flag;

	public void writer () {
		// 这两个数据之间没有数据依赖性，因此处理器会对这两行代码进行指令重排序
		d = 1;
		flag = true;
	}

	public void reader () {
		if(flag) {
			int b = d + 1;
			System.out.println(b);
		}
	}


}
