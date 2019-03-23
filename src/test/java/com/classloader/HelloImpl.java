package com.classloader;

public class HelloImpl implements Hello {

    @Override
    public void say(String s) {
        System.out.println("classLoader: " + this.getClass().getClassLoader() + "\t class: " + this.getClass() + "\t ========>>>> :" + s);
    }
}
