package com.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIODemoClientHandle implements Runnable {

    private String ip;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    public NIODemoClientHandle(String ip, int port) {
        this.ip = null == ip ? "127.0.0.1" : ip;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!stop) {
            try {
                //休眠1秒  无论是否有读写事件发生 selector每隔1秒被唤醒
                selector.select(1000);
                //获取注册在selector上的所有的就绪状态的serverSocketChannel中发生的事件
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterator = set.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();

                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleInput (SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) { //处于连接状态
                if (sc.finishConnect()) {//客户端连接成功
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);
                } else { //连接失败
                    System.exit(1);
                }
            }

            if (key.isReadable()) {//如果客户端接收到了服务器端发送的应答消息 则SocketChannel是可读的
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int bytes = sc.read(readBuffer);
                if (bytes > 0) {
                    readBuffer.flip();
                    byte[] byteArray = new byte[readBuffer.remaining()];
                    readBuffer.get(byteArray);
                    String responseMessage = new String(byteArray, "UTF-8");
                    System.out.println("the response message is：" + responseMessage);
                    this.stop = true;
                } else if (bytes < 0) {
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private void doConnect() throws IOException {
        //如果直连接连接成功，则注册到多路复用器上，并注册SelectionKey.OP_READ操作
        if (socketChannel.connect(new InetSocketAddress(ip, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);

        } else { // 如果直连接连接未成功，则注册到多路复用器上，并注册SelectionKey.OP_CONNECT操作
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    private void doWrite(SocketChannel socketChannel) throws IOException {
        String request = "request message from client";
        ByteBuffer writeBuffer = ByteBuffer.allocate(request.getBytes().length);
        writeBuffer.put(request.getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);

        // 如果缓冲区里面的所有内容全部发送完毕
        if (writeBuffer.hasRemaining()) {
            System.out.println("client send request message to server success!");
        }
    }


}
