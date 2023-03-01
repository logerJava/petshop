package com.loger.petshop.uaa.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author loger
 * @date 2023/2/27 13:44
 */
@Data
public class MyUser implements Serializable {

    private String account;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

}
