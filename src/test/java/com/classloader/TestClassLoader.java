package com.classloader;

import org.junit.Test;

import java.io.File;

/**
 * 自定义类加载器
 */
public class TestClassLoader extends ClassLoader {

    private String path =  TestClassLoader.class.getResource("/").getPath();
    private String testClass = "com.classloader.Hello";

    @Test
    public void hotClassLoader() throws Exception {
        File file = new File(path + "com/classloader/Hello.class");
        HotClassLoader hotClassLoader = new HotClassLoader();
        hotClassLoader.setObjFile(file);

        Class<?> clazz1 = hotClassLoader.findClass(testClass);
        System.out.println("hotClassLoader: clazz1 >>> " + clazz1.getClassLoader());
        System.out.println("appClassLoader: " + Hello.class.getClassLoader());

    }

    @Test
    public void myClassLoader() throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader(path + "com/classloader");

        Class<?> clazz1 =  myClassLoader.createClass(testClass);

        System.out.println("myClassLoader: clazz1 >>> " + clazz1.getClassLoader());
        System.out.println("hotClassLoader: " + Hello.class.getClassLoader());

    }

    @Test
    public void fileSystemClassLoader() {

        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        while (classLoader != null) {
            System.out.println(">>>>" + classLoader);
            classLoader = classLoader.getParent();
        }

        System.out.println("1111" + classLoader);

        FileSystemClassLoader loader1 = new FileSystemClassLoader(path);
        FileSystemClassLoader loader2 = new FileSystemClassLoader(path);

        try {
            Class<?> c1 = loader1.loadClass("com.classloader.Hello");
            Class<?> c2 = loader1.loadClass("com.classloader.Hello");
            Class<?> c3 = loader2.loadClass("com.classloader.Hello");

            Class<?> stringClass = loader2.loadClass("java.lang.String");
            Class<?> myClass = loader2.loadClass("com.classloader.TestClassLoader");

            System.out.println(c1.hashCode() + "\t" + c1.getClassLoader());

            // 同一个类，不用的类加载器，JVM 任务是不同的类
            System.out.println(c2.hashCode());
            System.out.println(c3.hashCode());

            System.out.println("stringClass:\t" + stringClass.hashCode() + "\t" + stringClass.getClassLoader());
            System.out.println("myClass:\t" + myClass.hashCode() + "\t" + myClass.getClassLoader());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
