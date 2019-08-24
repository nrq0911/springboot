package com.jvm;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.apache.lucene.util.RamUsageEstimator;

public class ObjectSize2 {

    public static void main(String[] args) {
        // 16 byte
        byte [] b = new byte[0];
        Object o = new Object();
        short [] s = new short[0];
        char [] c = new char[0];
        boolean [] bool = new boolean[0];
        int [] i = new int[0];
        long [] longs = new long[0];
        double [] doubles = new double[0];
        float [] floats = new float[0];

        // 24 byte
        double [] doubles2 = new double[1];
        boolean [] bools = new boolean[1];
        byte [] bytes = new byte[1];

        //计算指定对象及其引用树上的所有对象的综合大小，单位字节
        long l = RamUsageEstimator.sizeOf(b);
        //计算指定对象本身在堆空间的大小，单位字节
        long l1 = RamUsageEstimator.shallowSizeOf(b);


        System.out.println("object" + RamUsageEstimator.shallowSizeOf(o));


        System.out.print("byte " + l);
        System.out.println("\t byte " + l1);

        System.out.println("short " + RamUsageEstimator.sizeOf(s));
        System.out.println("char " + RamUsageEstimator.sizeOf(c));
        System.out.println("boolean " + RamUsageEstimator.sizeOf(bool));
        System.out.println("int " + RamUsageEstimator.sizeOf(i));
        System.out.println("long " + RamUsageEstimator.sizeOf(longs));
        System.out.println("double " + RamUsageEstimator.sizeOf(doubles));
        System.out.println("float " + RamUsageEstimator.sizeOf(floats));

        System.out.println("double2 " + RamUsageEstimator.sizeOf(doubles2));
        System.out.println("boolean2  " + RamUsageEstimator.sizeOf(bools));
        System.out.println("boolean2  " + ObjectSizeCalculator.getObjectSize(bools));
        System.out.println("byte2  " + RamUsageEstimator.sizeOf(bytes));

        System.out.println(RamUsageEstimator.shallowSizeOf(new ObjectSize.A()));
        System.out.println(RamUsageEstimator.shallowSizeOf(new ObjectSize.B()));
        System.out.println(RamUsageEstimator.shallowSizeOf(new ObjectSize.C()));
        System.out.println(RamUsageEstimator.shallowSizeOf(new ObjectSize.D()));



        System.out.println("thread size: " + RamUsageEstimator.shallowSizeOf(new Thread()));


    }
}
