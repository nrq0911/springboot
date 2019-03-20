package com.netty.heart;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ClienHeartBeattHandler extends ChannelInboundHandlerAdapter {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    private ScheduledFuture<?> heartBeat;
	//主动向服务器发送认证信息
    private InetAddress addr ;
    
    private static final String SUCCESS_KEY = "auth_success_key";

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();
		String key = "1234";
		//证书
		String auth = ip + "," + key;
		ctx.writeAndFlush(auth);
	}
	
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	try {
        	if(msg instanceof String){
        		String ret = (String)msg;
        		if(SUCCESS_KEY.equals(ret)){
        	    	// 握手成功，主动发送心跳消息
        	    	this.heartBeat = this.scheduler.scheduleWithFixedDelay(new HeartBeatTask(ctx), 0, 2, TimeUnit.SECONDS);
        		    System.out.println(msg);    			
        		}
        		else {
        			System.out.println(msg);
        		}
        	}
		} finally {
			ReferenceCountUtil.release(msg);
		}
    }

    private class HeartBeatTask implements Runnable {
    	private final ChannelHandlerContext ctx;

		public HeartBeatTask(final ChannelHandlerContext ctx) {
		    this.ctx = ctx;
		}
	
		@Override
		public void run() {
			try {
			    RequestInfo info = new RequestInfo();
			    //ip
			    info.setIp(addr.getHostAddress());

		        HashMap<String, Object> cpuPercMap = new HashMap<String, Object>();
		        cpuPercMap.put("combined", System.currentTimeMillis());
		        cpuPercMap.put("user", System.currentTimeMillis());
		        cpuPercMap.put("sys", System.currentTimeMillis());
		        cpuPercMap.put("wait", System.currentTimeMillis());
		        cpuPercMap.put("idle", System.currentTimeMillis());
		        // memory
				HashMap<String, Object> memoryMap = new HashMap<String, Object>();
				memoryMap.put("total", System.currentTimeMillis());
				memoryMap.put("used", System.currentTimeMillis());
				memoryMap.put("free", System.currentTimeMillis());
				info.setCpuPercMap(cpuPercMap);
			    info.setMemoryMap(memoryMap);
			    ctx.writeAndFlush(info);
			    
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	    	cause.printStackTrace();
			if (heartBeat != null) {
			    heartBeat.cancel(true);
			    heartBeat = null;
			}
			ctx.fireExceptionCaught(cause);
	    }
	    
	}
}
