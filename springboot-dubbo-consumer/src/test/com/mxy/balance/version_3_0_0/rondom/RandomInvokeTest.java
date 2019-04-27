package com.mxy.balance.version_3_0_0.rondom;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxy.api.facade.HealthCheckFacade;
import com.mxy.balance.version_3_0_0.DubboConsumerApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Random LoadBalance
 * 随机，按权重设置随机概率。
 * 在一个截面上碰撞的概率高，但调用量越大分布越均匀，而且按概率使用权重后也比较均匀，有利于动态调整提供者权重。
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DubboConsumerApplicationTest.class)
public class RandomInvokeTest {

    @Reference(version = "3.0.0", loadbalance = "random")
    private HealthCheckFacade healthCheckFacade;

    @Test
    public void test() {
        for (int i = 0; i < 50; i++) {
            log.info(healthCheckFacade.invoke());
        }
    }


}
