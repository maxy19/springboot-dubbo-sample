package com.mxy.balance.version_3_1_0_round.facade.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mxy.api.facade.HealthCheckFacade;

@Service(version = "3.1.0")
public class RoundFacadeImpl implements HealthCheckFacade {


    @Override
    public String invoke() {
        return "服务3收到....";
    }
}
