package com.hacker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Author: Zero
 * @Date: 2022/5/19 15:37
 * @Description:
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Value("${knife4j.description:商城服务}")
    private String description;

    @Value("${knife4j.groupName:默认分组-商城服务}")
    private String groupName;

    @Value("${knife4j.basePackage:com.hacker}")
    private String basePackage;

    @Value("${knife4j.version:1.0}")
    private String version;

    @Bean(value = "docketInfo")
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
    }

}
