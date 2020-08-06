package com.lzy.chatmovie.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.accept.ContentNegotiationStrategy;

/**
 * 安全配置类
 * @author lzy
 * @since 2020.2.3
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        /**
         * authorizeRequests 全注解实现的开端， 表示开始说明需要的权限
         * 需要的权限：
         * 1.拦截的路径
         * 2.访问该路径需要的权限
         * antMatchers 拦截所有路径，permitAll表示任何权限都可通行
         * anyRequest任何的请求 ，authenticated 认证后能访问
         *  .and().csrf().disable() 固定写法，使csrf 拦截实效
         */

        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

    }

    protected WebSecurityConfig() {
        super();
    }

    protected WebSecurityConfig(boolean disableDefaults) {
        super(disableDefaults);
    }


}
