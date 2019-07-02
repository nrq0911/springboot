package com.jvm;

import java.util.ArrayList;
import java.util.List;

public class ObjectSize {

    public static void main(String[] args) throws InterruptedException {
        final List<A> aList = new ArrayList<>();
        final List<B> bList = new ArrayList<>();
        final List<C> cList = new ArrayList<>();
        final List<D> dList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            aList.add(new A());
            bList.add(new B());
            cList.add(new C());
            dList.add(new D());
        }

        Thread.sleep(1000000);

    }


    public static class A {
    }

    public static class B {
        int i = 1;
    }

    public static class C {
        long i = 1L;
    }

    public static class D {
        String s = "1";
    }

}
