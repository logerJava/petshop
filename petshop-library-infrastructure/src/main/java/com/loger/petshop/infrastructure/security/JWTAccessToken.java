package com.loger.petshop.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author loger
 * @date 2023/2/27 16:56
 */
public class JWTAccessToken extends JwtAccessTokenConverter {

    @Autowired
    public JWTAccessToken(UserDetailsService userDetailsService) {
        // 设置从资源请求中带上来的JWT令牌转换回安全上下文中的用户信息的查询服务
        // 如果不设置该服务，则从JWT令牌获得的Principal就只有一个用户名（令牌中确实就只存了用户名）
        // 将用户用户信息查询服务提供给默认的令牌转换器，使得转换令牌时自动根据用户名还原出完整的用户对象
        // 这方便了后面编码（可以在直接获得登陆用户信息），但也稳定地为每次请求增加了一次（从数据库/缓存）查询，自行取舍
        DefaultUserAuthenticationConverter converter = new DefaultUserAuthenticationConverter();
        converter.setUserDetailsService(userDetailsService);
        ((DefaultAccessTokenConverter) getAccessTokenConverter()).setUserTokenConverter(converter);
    }

    // /**
    //  * 增强令牌
    //  * 增强主要就是在令牌的负载中加入额外的信息
    //  */
    // @Override
    // public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    //     Authentication user = authentication.getUserAuthentication();
    //     // 对于密码模式才会有用户信息，在微服务之间的客户端认证模式下不需要增强令牌
    //     if (user != null) {
    //         String[] authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);
    //         Map<String, Object> payLoad = new HashMap<>();
    //         // Spring Security OAuth的JWT令牌默认实现中就加入了一个“user_name”的项存储了当前用户名
    //         // 这里主要是出于演示Payload的用途，以及方便客户端获取（否则客户端要从令牌中解码Base64来获取），设置了一个“username”，两者的内容是一致的
    //         payLoad.put("username", user.getName());
    //         payLoad.put("authorities", authorities);
    //         payLoad.put("iss", "icyfenix@gmail.com");
    //         payLoad.put("sub", "bookstore");
    //         ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(payLoad);
    //     }
    //     return super.enhance(accessToken, authentication);
    // }

}
