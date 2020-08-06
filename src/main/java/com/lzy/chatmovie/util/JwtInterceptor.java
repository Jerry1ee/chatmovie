package com.lzy.chatmovie.util;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Jwt 拦截器
 * @author lzy
 * @since 2020.2.2
 */

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //拦截器只负责把请求头中包含token的令牌进行解析

        String header = request.getHeader("Authorization");
        System.out.println("经过了拦截器！");
        System.out.println(header);
        if(header!=null && !"".equals(header)){
            //如果包含头信息，对其进行解析
            //对令牌进行验证
            try{
                Claims claims = jwtUtil.parseJWT(header);
                if(claims!=null)
                    request.setAttribute("token",header);
            }
            catch (Exception e )
            {
                throw new RuntimeException("token错误，拒绝访问！");
            }

        }
        return true;
    }
}
