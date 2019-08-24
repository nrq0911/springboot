package com.classloader;

public class ClassForNameTest {

    public static void main(String[] args) throws Exception {

        Class<?> aClass = Class.forName("com.classloader.ClassForName");

        System.out.println("========================================");

        final Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("com.classloader.ClassForName");

    }

}
