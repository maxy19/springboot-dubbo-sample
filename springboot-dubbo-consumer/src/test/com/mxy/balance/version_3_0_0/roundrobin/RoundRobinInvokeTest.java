package com.mxy.balance.version_3_0_0.roundrobin;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxy.api.facade.HealthCheckFacade;
import com.mxy.balance.version_3_0_0.DubboConsumerApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * RoundRobin LoadBalance
 * 轮询，按公约后的权重设置轮询比率。
 * 存在慢的提供者累积请求的问题，比如：第二台机器很慢，但没挂，当请求调到第二台时就卡在那，久而久之，所有请求都卡在调到第二台上。
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DubboConsumerApplicationTest.class)
public class RoundRobinInvokeTest {

    @Reference(version = "3.1.0",loadbalance = "roundrobin")
    private HealthCheckFacade healthCheckFacade;

    @Test
    public void test(){
        for (int i = 0; i < 50; i++) {
            log.info(healthCheckFacade.invoke());
        }
    }


}
