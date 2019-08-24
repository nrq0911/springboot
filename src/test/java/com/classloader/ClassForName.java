package com.classloader;

public class ClassForName extends ClassForNameSuper {

    static {
        System.out.println("static code");
    }

    public static String str = createString();

    public ClassForName () {
        System.out.println("constructor method");
    }

    public static String createString() {
        System.out.println("static method");
        return "test";
    }

}
