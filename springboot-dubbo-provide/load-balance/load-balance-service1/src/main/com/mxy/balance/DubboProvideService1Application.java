package com.mxy.balance;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo(scanBasePackages = "com.mxy.balance.version_3_2_0_*.*")
@SpringBootApplication(scanBasePackages = "com.mxy.balance.version_3_2_0_*.*")
public class DubboProvideService1Application {
    public static void main(String[] args) {
        SpringApplication.run(DubboProvideService1Application.class, args);
    }
}
