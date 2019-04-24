package com.netty.nio;

public class NIOServer {

    public static void main(String[] args) {
        int port  = 8081;

        new Thread(new NIODemoServer(port), "NIO-MultiplexerTimerServer-001").start();
    }

}
