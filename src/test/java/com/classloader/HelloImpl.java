package com.classloader;

public class HelloImpl implements Hello {

    static {
        System.out.println("Hello static code");
    }

    {
        System.out.println("Hello code");
    }

    @Override
    public void say(String s) {
        System.out.println("classLoader: " + this.getClass().getClassLoader() + "\t class: " + this.getClass() + "\t ========>>>> :" + s);
    }
}
