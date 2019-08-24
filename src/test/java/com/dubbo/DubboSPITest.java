package com.dubbo;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.example.dubbo.Robot;
import org.junit.Test;

public class DubboSPITest {

    @Test
    public void sayHello() throws Exception {
        ExtensionLoader extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot bumblebee = (Robot)extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();

        System.out.println("============ split line ==================");

        Robot optimusPrime = (Robot)extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();

    }

}
