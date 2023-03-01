package com.loger.petshop.account.resource;

import com.loger.petshop.account.application.UserManagerService;
import com.loger.petshop.domain.account.Users;
import com.loger.petshop.domain.security.AuthenticAccount;
import com.loger.petshop.infrastructure.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户资源
 *
 * @author loger
 * @date 2023/2/24 11:29
 */
@RestController
@RequestMapping("/accounts")
public class AccountResource {

    @Autowired
    private UserManagerService userManagerService;


    @PostMapping("/createUser")
    public Result<Void> createUser(@RequestBody Users mallUsers){
        userManagerService.createAccount(mallUsers);
        return Result.success();
    }

    @PostMapping("/findByAccount")
    public AuthenticAccount findByAccount(String account){
        Users user = userManagerService.findByAccount(account);
        return new AuthenticAccount(user);
    }


}
