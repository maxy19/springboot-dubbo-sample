package com.mxy.balance.version_3_0_0.leastactive;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxy.api.facade.HealthCheckFacade;
import com.mxy.balance.version_3_0_0.DubboConsumerApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * LeastActive LoadBalance
 * 最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差。
 * 使慢的提供者收到更少请求，因为越慢的提供者的调用前后计数差会越大。
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DubboConsumerApplicationTest.class)
public class LeastActiveInvokeTest {

    @Reference(version = "3.2.0",loadbalance = "leastactive")
    private HealthCheckFacade healthCheckFacade;

    @Test
    public void test() {
        for (int i = 0; i < 50; i++) {
            log.info(healthCheckFacade.invoke());
        }
    }


}
