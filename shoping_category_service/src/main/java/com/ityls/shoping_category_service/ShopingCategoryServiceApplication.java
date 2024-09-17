package com.ityls.shoping_category_service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@EnableDiscoveryClient
@EnableDubbo
@RefreshScope
@SpringBootApplication
@MapperScan("com.ityls.shoping_category_service.mapper")
public class ShopingCategoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopingCategoryServiceApplication.class, args);
    }
}
