package com.loger.petshop.domain.account;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商城用户表
 * </p>
 *
 * @author loger
 * @since 2023-02-24
 */
@Data
@TableName("users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long uid;

    private String account;

    private String password;

    private String username;

    private String mobile;

    private LocalDateTime createTime;

    /**
     * 1：商城；2：CMS；3：OA；
     */
    private Integer type;

    /**
     * 启用状态: 未启用：0，启用：1
     */
    private Boolean enabled;

}
