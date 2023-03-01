package com.loger.petshop.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author loger
 * @date 2023/2/23 13:16
 * @description:
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.loger.petshop"})
public class UAA_Application {
    public static void main(String[] args) {
        SpringApplication.run(UAA_Application.class, args);
    }
}
