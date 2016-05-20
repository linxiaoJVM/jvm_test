package com.jvm.chapter02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lin on 2016/3/28.
 */
public class UniqueThreadIdGenerator {
    private static final AtomicInteger uniqueId = new AtomicInteger(0);

    private static final ThreadLocal < Integer > uniqueNum =
            new ThreadLocal < Integer > () {
                @Override protected Integer initialValue() {
                    return uniqueId.getAndIncrement();
                }
            };

    public static int getCurrentThreadId() {
        return uniqueId.get();
    }
}
