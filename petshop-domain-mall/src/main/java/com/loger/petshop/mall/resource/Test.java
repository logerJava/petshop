package com.loger.petshop.mall.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author loger
 * @date 2023/2/27 15:30
 */
@RestController
@RequestMapping("/test")
public class Test {

    /***
     * 读权限或写权限可访问，返回登录用户名
     * @param authentication
     * @return
     */
    @PreAuthorize("hasAuthority('READ') or hasAuthority('WRITE')")
    @GetMapping("name")
    public String name(OAuth2Authentication authentication) {
        return authentication.getName();
    }



}
