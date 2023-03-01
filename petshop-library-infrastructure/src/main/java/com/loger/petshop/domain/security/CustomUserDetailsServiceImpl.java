package com.loger.petshop.domain.security;

import com.loger.petshop.domain.account.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author loger
 * @date 2023/2/27 13:45
 */
@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountFeign accountFeign;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        // RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        // HttpServletRequest request = attributes.getRequest();
        AuthenticAccount byAccount = accountFeign.findByAccount(account);

        return Optional.ofNullable(accountFeign.findByAccount(account)).orElseThrow(() -> new UsernameNotFoundException("未找到该用户:" + account));
    }



}
