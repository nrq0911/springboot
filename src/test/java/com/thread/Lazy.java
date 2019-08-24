package com.thread;

public class Lazy {

    private static boolean init = false;

    static {
        /*Thread thread = new Thread( () -> {
            //init = true;
            System.out.println(Thread.currentThread().getName());
        });*/

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // init = true;
                System.out.println(Thread.currentThread().getName());
            }
        });

        thread.start();
        try {
            System.out.println(Thread.currentThread().getName());
            thread.join();
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName());
        }

    }


    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getName());
        System.out.println(init);
    }



}
