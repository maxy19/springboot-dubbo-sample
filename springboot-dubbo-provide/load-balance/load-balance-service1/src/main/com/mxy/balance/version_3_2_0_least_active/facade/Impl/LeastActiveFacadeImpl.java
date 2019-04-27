package com.mxy.balance.version_3_2_0_least_active.facade.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mxy.api.facade.HealthCheckFacade;

@Service(version = "3.2.0")
public class LeastActiveFacadeImpl implements HealthCheckFacade {


    @Override
    public String invoke() {
        return "服务1收到....";
    }
}
