package com.loger.petshop.domain.security;

/**
 *
 * 角色常量类
 * 目前系统中只有2种角色：用户，管理员
 * 为了注解{@link javax.annotation.security.RolesAllowed}中使用方便，定义为字符串常量（非枚举）
 *
 * @author loger
 * @date 2023/2/28 11:50
 */
public interface Role {
    String USER = "ROLE_USER";
    String ADMIN = "ROLE_ADMIN";
}
