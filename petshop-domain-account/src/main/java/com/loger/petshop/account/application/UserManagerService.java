package com.loger.petshop.account.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loger.petshop.account.domain.repository.UsersServices;
import com.loger.petshop.domain.account.Users;
import com.loger.petshop.infrastructure.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author loger
 * @date 2023/2/24 10:43
 */
@Component
public class UserManagerService {

    @Autowired
    private UsersServices usersServices;

    @Autowired
    private Encryption encryption;

    @Transactional(rollbackFor = Exception.class)
    public void createAccount(Users mallUsers){
        // TODO: 事务好像不好使
        mallUsers.setPassword(encryption.encode(mallUsers.getPassword()));
        usersServices.save(mallUsers);
    }

    public Users findByAccount(String account){
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("account",account)
                .eq("enabled",1);



        return usersServices.getOne(wrapper);
    }



}
