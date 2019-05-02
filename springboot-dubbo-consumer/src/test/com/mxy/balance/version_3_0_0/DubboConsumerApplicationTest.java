package com.mxy.balance.version_3_0_0;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo(scanBasePackages = "com.mxy.consumer.version_3_0_0.*")
@SpringBootApplication(scanBasePackages = "com.mxy.balance.version_3_0_0.*")
public class DubboConsumerApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplicationTest.class, args);
    }
}
