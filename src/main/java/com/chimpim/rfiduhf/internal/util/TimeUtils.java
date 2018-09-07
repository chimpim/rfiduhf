package com.chimpim.rfiduhf.internal.util;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

    // 休眠
    public static void sleep(long milliseconds) {
        sleep(milliseconds, TimeUnit.MILLISECONDS);
    }

    // 休眠
    public static void sleep(long timeout, @NotNull TimeUnit timeUnit) {
        try {
            timeUnit.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
