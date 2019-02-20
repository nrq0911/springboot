package com.zookeeper.curator.barrier;

import java.util.Random;

import com.zookeeper.Constant;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorBarrier1 {
	
	public static void main(String[] args) throws Exception {

		for(int i = 0; i < 5; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
						CuratorFramework cf = CuratorFrameworkFactory.builder()
									.connectString(Constant.CONNECT_ADDR)
									.retryPolicy(retryPolicy)
									.build();
						cf.start();
						
						DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(cf, "/super", 5);
						Thread.sleep(1000 * (new Random()).nextInt(3)); 
						System.out.println(Thread.currentThread().getName() + "已经准备");
						barrier.enter();
						System.out.println("同时开始运行...");
						Thread.sleep(1000 * (new Random()).nextInt(3));
						System.out.println(Thread.currentThread().getName() + "运行完毕");
						barrier.leave();
						System.out.println("同时退出运行...");
						

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			},"t" + i).start();
		}

		
		
	}
}
