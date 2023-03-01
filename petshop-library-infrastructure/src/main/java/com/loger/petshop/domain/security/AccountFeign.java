package com.loger.petshop.domain.security;

import com.loger.petshop.domain.account.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author loger
 * @date 2023/2/24 17:01
 */
@FeignClient(value = "petshop-account")
@Component
public interface AccountFeign {

    @PostMapping("/accounts/findByAccount")
    AuthenticAccount findByAccount(@RequestParam("account") String account);

}
