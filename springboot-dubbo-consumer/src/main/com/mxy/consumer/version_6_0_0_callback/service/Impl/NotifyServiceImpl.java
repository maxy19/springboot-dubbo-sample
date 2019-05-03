package com.mxy.consumer.version_6_0_0_callback.service.Impl;

import com.mxy.consumer.version_6_0_0_callback.service.Notify;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotifyServiceImpl implements Notify {
    @Override
    public void onReturn(String msg) {
        log.info("=========onReturn = {} ", msg);
    }

    @Override
    public void testThrow(Throwable ex) {
        log.error("=======returnThrow = {}", ex.getMessage());
    }
}
