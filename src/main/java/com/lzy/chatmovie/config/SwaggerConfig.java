package com.lzy.chatmovie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * swagger 注解
 * @author lzy
 * @since 2020.2.2
 */

@Configuration
@EnableSwagger2                   //开启swagger2
public class SwaggerConfig {


    private static final String API_PACKAGE_NAME = "com.lzy.chatmovie";
    //配置Swagger的Bean实例
    @Bean
    public Docket docket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(API_PACKAGE_NAME))
                .build();
    }

    //配置swagger信息 apiInfo
    private ApiInfo apiInfo()
    {
        Contact contact=new Contact("lzy", "", "cqulizhiying@foxmail.com");
        return new ApiInfo(
                "chatmovie API 文档",
                "Api Documentation",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());

    }
}
