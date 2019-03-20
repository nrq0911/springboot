package com.classloader;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 *  自定义类加载器
 */
public class FileSystemClassLoader extends ClassLoader {

    /** 指定这个类加载目录 */
    private String rootDir;


    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> clazz = findLoadedClass(name);
        if (clazz != null) {
            return clazz;
        }

        ClassLoader parent = this.getParent();

        try {
            clazz = parent.loadClass(name);
        } catch (ClassNotFoundException e) {}

        if (clazz != null) {
            return clazz;
        } else {
            try {
                byte [] classData = getClassData(name);
                if (null == classData) {
                    throw new ClassNotFoundException();
                } else {
                    clazz = defineClass(name, classData, 0, classData.length);
                    return clazz;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return clazz;
    }

    private byte[] getClassData(String className) throws IOException {
        String path = this.rootDir + "/" + className.replace(".", "/") + ".class";
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        FileChannel fileC = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel outC = Channels.newChannel(baos);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            int i = fileC.read(buffer);
            if (i == 0 || i == -1) {
                break;
            }
            buffer.flip();
            outC.write(buffer);
            buffer.clear();
        }
        fis.close();
        return baos.toByteArray();
    }


}
