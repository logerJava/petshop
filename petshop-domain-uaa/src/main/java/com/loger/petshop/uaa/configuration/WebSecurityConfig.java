package com.loger.petshop.uaa.configuration;

import com.loger.petshop.domain.security.CustomUserDetailsServiceImpl;
import com.loger.petshop.infrastructure.config.ResourceServerConfiguration;
import com.loger.petshop.uaa.provider.PreAuthenticatedAuthenticationProvider;
import com.loger.petshop.uaa.provider.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author loger
 * @date 2023/2/23 13:33
 * @description:
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private UsernamePasswordAuthenticationProvider userProvider;

    @Autowired
    private PreAuthenticatedAuthenticationProvider preProvider;

    /**
     * 需要把AuthenticationManager主动暴漏出来
     * 以便在授权服务器{@link OAuth2ServerConfiguration}中可以使用它来完成用户名、密码的认证
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置Spring Security的安全认证服务
     * Spring Security的Web安全设置，将在资源服务器配置{@link ResourceServerConfiguration}中完成
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(userProvider);
        auth.authenticationProvider(preProvider);
    }

    /**
     * 开放 /login/** 和 /oauth/authorize 两个路径的匿名访问。前者用于登录，后者用于换授权码，这两个端点访问的时机都在登录之前。
     * 设置 /login 使用表单验证进行登录。
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/login/**", "/oauth/authorize")
                .permitAll()
                .anyRequest().authenticated();
    }





}
