package com.mxy.balance.version_3_0_0.leastactive;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxy.api.facade.HealthCheckFacade;
import com.mxy.balance.version_3_0_0.DubboConsumerApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * LeastActive LoadBalance
 * 配置服务的客户端的loadbalance属性为 leastactive，此 Loadbalance 会调用并发数最小的
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DubboConsumerApplicationTest.class)
public class LeastActiveInvokeTest {

    @Reference(version = "3.2.0", loadbalance = "leastactive")
    private HealthCheckFacade healthCheckFacade;

    @Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    log.info(healthCheckFacade.invoke());
                }
            });
        }
    }
}

