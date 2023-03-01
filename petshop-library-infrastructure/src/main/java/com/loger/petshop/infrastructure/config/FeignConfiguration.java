package com.loger.petshop.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author loger
 * @date 2023/2/27 10:22
 */
@Configuration
@EnableFeignClients(basePackages = {"com.loger.petshop"})
public class FeignConfiguration {


}
