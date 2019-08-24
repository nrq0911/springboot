package com.classloader;

public class ClassForNameSuper {

    static {
        System.out.println("super class static code");
    }

    public static String str = createString();

    private static String createString() {
        System.out.println("super class static method");
        return "super class test";
    }

    public ClassForNameSuper () {
        System.out.println("super class constructor method");
    }
}
