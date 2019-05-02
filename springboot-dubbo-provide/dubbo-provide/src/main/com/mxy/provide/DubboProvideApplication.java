package com.mxy.provide;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo(scanBasePackages = "com.mxy.provide.version_4_0_0_cache")
@SpringBootApplication(scanBasePackages = "com.mxy.provide.version_4_0_0_cache")
public class DubboProvideApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboProvideApplication.class, args);
    }
}
