package com.thread.singleton;

import java.util.concurrent.atomic.AtomicReference;

public class CASSingleton {

    private static final AtomicReference<CASSingleton> INSTANCE = new AtomicReference<>();

    private CASSingleton(){
    }

    private static CASSingleton getSingleton() {
        for(;;) {
            CASSingleton casSingleton = INSTANCE.get();
            if (casSingleton == null) {
                if(INSTANCE.compareAndSet(null, new CASSingleton())) {
                    return INSTANCE.get();
                }
            } else {
                return casSingleton;
            }
        }
    }

}
