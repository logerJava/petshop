package com.loger.petshop.domain.security;

/**
 * 访问范围常量类
 *
 * @author loger
 * @date 2023/2/27 17:01
 */
public interface Scope {

    /**
     * 客户端
     */
    String BROWSER = "BROWSER";
    /**
     * 微服务
     */
    String SERVICE = "SERVICE";

}
