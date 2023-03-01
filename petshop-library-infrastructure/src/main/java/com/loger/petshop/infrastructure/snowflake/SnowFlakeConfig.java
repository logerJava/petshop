package com.loger.petshop.infrastructure.snowflake;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;

import javax.annotation.PostConstruct;

/**
 * 雪花 ID 配置
 * @author loger
 * @date 2023/2/23 15:10
 */
public class SnowFlakeConfig {

    @PostConstruct
    public void init(){
        IdGeneratorOptions options = new IdGeneratorOptions((short) 9527);
        YitIdHelper.setIdGenerator(options);
    }

}
