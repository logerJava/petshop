package com.loger.petshop.infrastructure.mybatis;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * 数据库表实体类生成
 *
 * @author loger
 * @date 2022/4/21 13:14
 */
public class CodeGenerate {

    static String url = "jdbc:mysql://127.0.0.1:3306/petshop_account";
    static String userName = "root";
    static String passWord = "123456";


    public static void main(String[] args) {

        FastAutoGenerator.create(url, userName, passWord)
                .globalConfig(builder -> {
                    builder.author("loger") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            .outputDir("D://mybatisplus-generate"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.loger.petshop.domain") // 设置父包名
                            .moduleName("petshop-library-infrastructure") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "D://mybatisplus-generate")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("authorities"); // 设置需要生成的表名
                            // .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                // .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }


}
