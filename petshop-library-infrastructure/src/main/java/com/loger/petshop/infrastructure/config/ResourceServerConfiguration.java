package com.loger.petshop.infrastructure.config;

import com.loger.petshop.infrastructure.security.JWTAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * @author loger
 * @date 2023/2/27 16:09
 */
@Configuration
@EnableResourceServer //启用资源服务器
@EnableGlobalMethodSecurity(prePostEnabled = true) //启用方法注解方式来进行权限控制
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private JWTAccessTokenService tokenService;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenServices(tokenService);
    }

    /**
     * 配置HTTP访问相关的安全选项
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 基于JWT来绑定用户状态，所以服务端可以是无状态的
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 关闭CSRF（Cross Site Request Forgery）跨站请求伪造的防御
        // 因为需要状态存储CSRF Token才能开启该功能
        http.csrf().disable();
        // 关闭HTTP Header中的X-Frame-Options选项，允许页面在frame标签中打开
        http.headers().frameOptions().disable();
        // 关闭HTTP Header中的Cache-Control:no-cache，允许缓存响应结果
        http.headers().cacheControl().disable();
        // 设置服务的默认安全规则：
        // 在HTTP过滤器层面，在所有的服务都允许未认证的访问
        // 在方法安全层面，每个方法上设置所需要的认证、授权规则
        // 即采用方式二来控制权限
        http.authorizeRequests().anyRequest().permitAll();
    }



}
