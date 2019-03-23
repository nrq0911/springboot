package com.classloader;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo {

    private static Logger logger = Logger.getLogger(ProxyDemo.class);

    @Test
    public void proxy () {

        new StaticProxiedHello().say("static proxy");

        System.out.println("\t\t\t\t\t\t 分割线 \t\t\t\t\t\t");

        Hello hello = (Hello) new LogInvocationHandler(new HelloImpl()).newProxyInstance();
        hello.say("dynamic proxy");
        System.err.println(hello.getClass());

        System.out.println("\t\t\t\t\t\t 分割线 \t\t\t\t\t\t");

        Class<?> clazz = HelloImpl.class;
        HelloImpl cglibHello =  (HelloImpl) new CglibProxy().newProxyInstance(clazz);
        cglibHello.say("cglib dynamic proxy");
        System.err.println(cglibHello.getClass());

    }

    // 静态代理方式
    static class StaticProxiedHello implements Hello{
        private Hello hello = new HelloImpl();
        @Override
        public void say(String str) {
            logger.info("You said: " + str);
            hello.say(str);
        }
    }

    // Java Proxy 1. 首先实现一个InvocationHandler，方法调用会被转发到该类的invoke()方法。
    static class LogInvocationHandler implements InvocationHandler {

        private Object proxied;

        /**
         * @param proxied 被代理对象
         */
        public LogInvocationHandler(Object proxied) {
            this.proxied = proxied;
        }

        /**
         * 返回代理对象
         * @return
         */
        public Object newProxyInstance() {
            return Proxy.newProxyInstance(proxied.getClass().getClassLoader(), proxied.getClass().getInterfaces(), this);
        }

        /**
         *
         * @param proxy 代理对象
         * @param method 代理方法
         * @param args 方法参数
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //将代理对象生成字节码到F盘上，方便反编译出java文件查看，实际动态代理是不需要自己生成的
            System.out.println("method:"+method.getName());
            System.out.println("args:"+args[0].getClass().getName());
            System.out.println("Before invoke method...");
            Object object=method.invoke(proxied, args);
            System.out.println("After invoke method...");
            return object;
        }

    }


    static class CglibProxy implements MethodInterceptor {
        private Enhancer enhancer = new Enhancer();

        /**
         *
         * @param o 是被代理对象
         * @param method 调用方法的Method对象
         * @param args 方法参数
         * @param methodProxy
         * @return cglib生成用来代替Method对象的一个对象，使用MethodProxy比调用JDK自身的Method直接执行方法效率会有提升
         * @throws Throwable
         */
        @Override
        public Object intercept(Object o, Method method, Object[] args,
                                MethodProxy methodProxy) throws Throwable {
            System.out.println("before " + methodProxy.getSuperName());
            System.out.println(method.getName());
            Object o1 = methodProxy.invokeSuper(o, args);
            //Object o2 = method.invoke(o, args); 使用这种方式会发生死循环，因为方法会被拦截
            System.out.println("after " + methodProxy.getSuperName());
            return o1;
        }

        public  Object newProxyInstance(Class<?> c) {
            //设置产生的代理对象的父类。
            enhancer.setSuperclass(c);
            //设置CallBack接口的实例
            enhancer.setCallback(this);
            //使用默认无参数的构造函数创建目标对象
            return enhancer.create();
        }
    }
}
