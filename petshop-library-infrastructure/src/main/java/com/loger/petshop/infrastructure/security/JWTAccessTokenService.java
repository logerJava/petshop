package com.loger.petshop.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author loger
 * @date 2023/2/27 16:55
 */
@Component
public class JWTAccessTokenService extends DefaultTokenServices {

    /**
     * 构建JWT令牌，并进行默认的配置
     */
    @Autowired
    public JWTAccessTokenService(JWTAccessToken token,
                                 CustomClientDetailsServiceImpl clientService,
                                 Optional<AuthenticationManager> authenticationManager) {
        // 设置令牌的持久化容器
        // 令牌持久化有多种方式，单节点服务可以存放在Session中，集群可以存放在Redis中
        // 而JWT是后端无状态、前端存储的解决方案，Token的存储由前端完成
        setTokenStore(new JwtTokenStore(token));
        // 令牌支持的客户端详情
        setClientDetailsService(clientService);
        // 设置验证管理器，在鉴权的时候需要用到
        setAuthenticationManager(authenticationManager.orElseGet(() -> {
            OAuth2AuthenticationManager manager = new OAuth2AuthenticationManager();
            manager.setClientDetailsService(clientService);
            manager.setTokenServices(this);
            return manager;
        }));
        // 定义令牌的额外负载
        setTokenEnhancer(token);
        // access_token有效期，单位：秒
        setAccessTokenValiditySeconds(60 * 60 * 24 * 365 * 50);
        // refresh_token的有效期，单位：秒, 默认30天
        // 这决定了客户端选择“记住当前登录用户”的最长时效，即失效前都不用再请求用户赋权了
        setRefreshTokenValiditySeconds(60 * 60 * 24 * 15);
        // 是否支持refresh_token，默认false
        setSupportRefreshToken(true);
        // 是否复用refresh_token，默认为true
        // 如果为false，则每次请求刷新都会删除旧的refresh_token，创建新的refresh_token
        setReuseRefreshToken(true);
    }
}
