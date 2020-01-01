package com.wenge.data.entry.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: 蔡武
 * @Date: 2019/12/26 11:48
 * @Description: swagger2配置类
 */
@EnableSwagger2
@Configuration
@ConditionalOnExpression(value="${swagger.enabled}")
public class Swagger2Configuration {

    @Bean
    public Docket createManagerRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("人才实体数据录入平台")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wenge.data.entry.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("人才实体数据录入平台api文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
