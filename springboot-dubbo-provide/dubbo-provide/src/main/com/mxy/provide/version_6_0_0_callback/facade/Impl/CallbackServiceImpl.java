package com.mxy.provide.version_6_0_0_callback.facade.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.base.Preconditions;
import com.mxy.api.facade.callback.CallBackServcie;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service(version = "6.0.0")
@Slf4j
public class CallbackServiceImpl implements CallBackServcie {

    @Override
    public String addListener(String msg) {
        log.info("privode = " + getChanged());
        Preconditions.checkArgument(msg != null);
        return getChanged();
    }

    private String getChanged() {
        return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
