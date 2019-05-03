package com.mxy.consumer.version_6_0_0_callback.service;

public interface Notify {

    void onReturn(String msg);

    void testThrow(Throwable ex);
}
