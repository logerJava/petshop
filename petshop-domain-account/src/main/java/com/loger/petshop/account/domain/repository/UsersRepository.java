package com.loger.petshop.account.domain.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loger.petshop.domain.account.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author loger
 * @date 2023/2/24 9:46
 */
@Mapper
public interface UsersRepository extends BaseMapper<Users> {
}
