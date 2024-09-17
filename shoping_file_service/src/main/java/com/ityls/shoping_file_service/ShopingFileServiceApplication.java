package com.ityls.shoping_file_service;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@EnableDubbo
@RefreshScope
@Import(FdfsClientConfig.class) // 手动导入FastDFS配置类
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ShopingFileServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopingFileServiceApplication.class, args);
    }
}
