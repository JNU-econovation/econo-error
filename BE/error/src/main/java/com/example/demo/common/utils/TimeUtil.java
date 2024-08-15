package com.example.demo.common.utils;

public class TimeUtil {
    private TimeUtil() {}

    public static int convertSecondsFromMillis(long milliseconds) {
        return (int) (milliseconds / 1000);
    }
}