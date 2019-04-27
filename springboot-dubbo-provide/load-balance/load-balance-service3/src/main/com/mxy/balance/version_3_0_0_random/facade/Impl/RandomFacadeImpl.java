package com.mxy.balance.version_3_0_0_random.facade.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mxy.api.facade.HealthCheckFacade;

@Service(version = "3.0.0")
public class RandomFacadeImpl implements HealthCheckFacade {


    @Override
    public String invoke() {
        return "服务3收到....";
    }
}
