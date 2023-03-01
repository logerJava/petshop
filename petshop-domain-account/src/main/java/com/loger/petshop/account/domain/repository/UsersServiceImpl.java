package com.loger.petshop.account.domain.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loger.petshop.domain.account.Users;
import org.springframework.stereotype.Service;

/**
 * @author loger
 * @date 2023/2/24 10:10
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersRepository, Users> implements UsersServices {
}
