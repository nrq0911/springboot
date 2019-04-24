package com.netty.nio;

public class NIODemoClient {

    public static void main(String[] args) {
        int port = 8081;
        new Thread(new NIODemoClientHandle("127.0.0.1", port)).start();
    }

}
