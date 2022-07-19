package com.ssm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //配置了swagger的Docket的Bean实例
    //RequestHandlerSelectors 配置要扫描的接口的方式
    //basePackage 指定要扫描的包
    //any()全部扫描
    //none()全部不扫描
    //withClassAnnotation() 扫描类的注解，参数是一个注解的反射对象
    //withMethodAnnotation() 扫描方法的注解，参数是一个注解的反射对象
    //paths()过滤路径
    //enable()配置swagger是否启动，False则swagger不能在浏览器中访问

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ssm.controller"))
                .paths(PathSelectors.ant("/user/**"))
                .build();
    }

    //配置swagger信息（Info）
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("SpringBoot 继承swagger2 文档 by cx")
                .contact(new Contact("cx","xxxxx","ycxself@163.com"))
                .version("1.0")
                .description("API描述")
                .build();
    }
}
