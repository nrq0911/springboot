package com.netty;

import io.netty.util.internal.SystemPropertyUtil;

public class NettyTest {

    public static void main(String[] args) {
        int i = SystemPropertyUtil.getInt("io.netty.selectorAutoRebuildThreshold", 512);
        System.out.println(i);
    }

}
