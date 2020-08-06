package com.lzy.chatmovie.user.controller;

import com.lzy.chatmovie.user.model.po.User;
import com.lzy.chatmovie.user.model.vo.UserVo;
import com.lzy.chatmovie.user.service.UserService;
import com.lzy.chatmovie.util.JwtUtil;
import com.lzy.chatmovie.util.Result;
import com.lzy.chatmovie.util.StatusCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * author:Jerry1ee
 * time:2020.2.1
 *
 */
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @ApiOperation(value = "登录功能")
    @PostMapping("/login")
    public Result login(
            @ApiParam(value = "用户信息")
            @RequestBody UserVo userVo
            )
    {
        User newUser = userService.login(userVo.getMobilePhone(),userVo.getPassword());
        if(newUser==null)
            return new Result(false, StatusCode.LOGINERROR,"登录失败！");

        //前后端可以通信，采用JWT实现
        //生成令牌
        String token = jwtUtil.createJWT(newUser.getId().toString(),newUser.getName());
        //可以根据前端的需要返回内容
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("phoneNumber",newUser.getMobilePhone());
        map.put("id",newUser.getId().toString());
        map.put("name",newUser.getName());
        //目前不需要角色信息，所以生成令牌时没定义角色，返回前端也只含token
        return new Result(true,StatusCode.OK,"登录成功！",map);
    }


}
