package com.ityls.shoping_manager_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

// 由于使用了mp,默认会配置数据源，由于是服务的消费者，不需要配置（忽略数据源自动配置）
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient //向注册中心注册该服务
@RefreshScope // 配置动态刷新
public class ShopingManagerApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopingManagerApiApplication.class, args);
    }
}
