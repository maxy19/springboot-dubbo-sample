package com.mxy.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 测试事件回调时候 注释掉@EnableDubbo
 */
@EnableDubbo(scanBasePackages = "com.mxy.consumer.version_7_0_0_concurrent")
@SpringBootApplication(scanBasePackages = "com.mxy.consumer.version_7_0_0_concurrent")
public class DubboConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }
}
