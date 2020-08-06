# Swagger

## 引入Swagger

### 官网找swagger依赖

https://mvnrepository.com/

搜索	springfox-swag，添加如下两个依赖

## [Springfox Swagger2](https://mvnrepository.com/artifact/io.springfox/springfox-swagger2)

## [Springfox Swagger UI](https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui)

### 创建swaggerConfig类

```java
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 注解
 * @author lzy
 * @since 2020.2.2
 */

@Configuration
@EnableSwagger2                   //开启swagger2
public class SwaggerConfig {
}
```

### 启动springboot项目，访问

http://localhost:8080/swagger-ui.html#/



## 配置Swagger

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    //配置Swagger的Bean实例
    @Bean
    public Docket docket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    //配置swagger信息 apiInfo
    private ApiInfo apiInfo()
    {
        //配置扫描包
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
```



### 配置Swagger 扫描接口



```java
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
```

目前配置成这样基本够用