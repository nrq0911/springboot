package com.example.dubbo;

import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface Robot {
    void sayHello();
}
