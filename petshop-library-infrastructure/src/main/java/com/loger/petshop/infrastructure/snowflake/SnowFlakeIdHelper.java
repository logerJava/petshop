package com.loger.petshop.infrastructure.snowflake;

import com.github.yitter.idgen.YitIdHelper;

/**
 * 获取雪花 ID 工具类
 * @author loger
 * @date 2023/2/23 15:17
 */
public class SnowFlakeIdHelper {

    public static long nextId(){
        return YitIdHelper.nextId();
    }

}
