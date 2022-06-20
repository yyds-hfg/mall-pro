package com.hacker.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Zero
 * @Date: 2022/5/19 15:37
 * @Description:
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Knife4jConfiguration {

    @Value("${knife4j.description:商城服务}")
    private String description;

    @Value("${knife4j.groupName:默认分组-商城服务}")
    private String groupName;

    @Value("${knife4j.basePackage:com.hacker}")
    private String basePackage;

    @Value("${knife4j.version:1.0}")
    private String version;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description(description)
                .contact(new Contact("Roc-xb", "https://yang-roc.blog.csdn.net/", "aida_pc@qq.com"))
                .version(version)
                .title("knife4j在线API接口文档")
                .build();
    }

    /**@Bean(value = "docketInfo")
    public Docket docketInfo() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description(description)
                        .termsOfServiceUrl("https://www.google.com/")
                        .version(version)
                        .build())
                //分组名称
                .groupName(groupName)
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }*/

}
