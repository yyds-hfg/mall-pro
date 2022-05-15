package com.atguigu.common.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Create with Intellij IDEA.
 * Description：
 * User:Zero
 * Date:2021/7/30
 * Time:23:11
 */
@Configuration
@Profile(value = {"dev"})
public class SwaggerConfig {

    @Value("${swagger.groupname}")
    private String groupname;

    @Value("${swagger.title}")
    private String title;
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupname)
                .apiInfo(webApiInfo())
                .select()
                //.paths(Predicates.not(PathSelectors.regex("/admin/.*")))  //如果接口包含这个路径 是不会显示的
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
        //分组   api信息
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title(title)
                .description("本文档描述了微服务接口定义")
                .version("1.0")
                .contact(new Contact("韩福贵", "https://kaifa.baidu.com/", "1841814080@qq.com"))
                .build();
    }

}

