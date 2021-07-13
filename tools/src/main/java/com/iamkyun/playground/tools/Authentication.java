package com.iamkyun.playground.tools;

public final class Authentication {
    private static final ThreadLocal<Long> currentUserHolder = new ThreadLocal<>();

    public static Long getCurrentUserId() {
        return currentUserHolder.get();
    }

    public static void setCurrentUserId(Long userId) {
        currentUserHolder.set(userId);
    }
}
