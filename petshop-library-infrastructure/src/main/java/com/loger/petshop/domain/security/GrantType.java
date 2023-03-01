package com.loger.petshop.domain.security;

/**
 * @author loger
 * @date 2023/2/27 16:59
 */
public interface GrantType {

    // 四种标准类型
    String PASSWORD = "password";
    String CLIENT_CREDENTIALS = "client_credentials";
    String IMPLICIT = "implicit";
    String AUTHORIZATIONCODE = "authorizationcode";
    // 用于刷新令牌
    String REFRESH_TOKEN = "refresh_token";

}
