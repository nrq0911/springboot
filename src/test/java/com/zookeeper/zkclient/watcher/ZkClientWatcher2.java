package com.zookeeper.zkclient.watcher;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

import static com.zookeeper.Constant.CONNECT_ADDR;

public class ZkClientWatcher2 {

	
	public static void main(String[] args) throws Exception {
		ZkClient zkc = new ZkClient(new ZkConnection(CONNECT_ADDR), 5000);
		
		zkc.createPersistent("/super", "1234");
		
		//对父节点添加监听子节点变化。
		zkc.subscribeDataChanges("/super", new IZkDataListener() {
			@Override
			public void handleDataDeleted(String path) throws Exception {
				System.out.println("删除的节点为:" + path);
			}
			
			@Override
			public void handleDataChange(String path, Object data) throws Exception {
				System.out.println("变更的节点为:" + path + ", 变更内容为:" + data);
			}
		});
		
		Thread.sleep(3000);
		zkc.writeData("/super", "456", -1);
		Thread.sleep(1000);

		zkc.delete("/super");
		Thread.sleep(Integer.MAX_VALUE);
		
		
	}
}
