package com.loger.petshop.uaa.configuration;

import com.loger.petshop.domain.security.CustomUserDetailsServiceImpl;
import com.loger.petshop.infrastructure.security.JWTAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import javax.sql.DataSource;

/**
 * Spring Security OAuth2 授权服务器配置
 *
 * 在该配置中，设置了授权服务Endpoint的相关信息（端点的位置、请求方法、使用怎样的令牌、支持怎样的客户端）
 * 以及针对OAuth2的密码模式所需要的用户身份认证服务和用户详情查询服务
 *
 * @author loger
 * @date 2023/2/23 13:28
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTAccessTokenService tokenService;

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    /**
     * 我们配置了使用数据库来维护客户端信息。虽然在各种Demo中我们经常看到的是在内存中维护客户端信息，通过配置直接写死在这里。
     * 但是，对于实际的应用我们一般都会用数据库来维护这个信息，甚至还会建立一套工作流来允许客户端自己申请ClientID，实现OAuth客户端接入的审批。
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    /**
     * 使用JDBC数据库方式来保存授权码
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 配置授权的服务Endpoint
     * <p>
     * Spring Security OAuth2会根据配置的认证服务、用户详情服务、令牌服务自动生成以下端点：
     * /oauth/authorize：授权端点
     * /oauth/token：令牌端点
     * /oauth/confirm_access：用户确认授权提交端点
     * /oauth/error：授权服务错误信息端点
     * /oauth/check_token：用于资源服务访问的令牌解析端点
     * /oauth/token_key：提供公有密匙的端点，如果JWT采用的是非对称加密加密算法，则资源服务其在鉴权时就需要这个公钥来解码
     * 如有必要，这些端点可以使用pathMapping()方法来修改它们的位置，使用prefix()方法来设置路径前缀
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenServices(tokenService)
                //控制TokenEndpoint端点请求访问的类型，默认HttpMethod.POST
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    /**
     * 配置OAuth2发布出来的Endpoint本身的安全约束
     * <p>
     * 这些端点的默认访问规则原本是：
     * 1. 端点开启了HTTP Basic Authentication，通过allowFormAuthenticationForClients()关闭，即允许通过表单来验证
     * 2. 端点的访问均为denyAll()，可以在这里通过SpringEL表达式来改变为permitAll()
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
    }

    // /**
    //  * 配置JWT使用非对称加密方式来验证
    //  */
    // @Bean
    // protected JwtAccessTokenConverter jwtTokenEnhancer() {
    //     KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("petshopkey.jks"), "123456".toCharArray());
    //     JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    //     converter.setKeyPair(keyStoreKeyFactory.getKeyPair("petshopkey"));
    //     return converter;
    // }

}
