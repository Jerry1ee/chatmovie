package com.lzy.chatmovie.user.controller;

import com.lzy.chatmovie.user.model.dto.RegisterDTO;
import com.lzy.chatmovie.user.model.po.User;
import com.lzy.chatmovie.user.model.vo.UserVo;
import com.lzy.chatmovie.user.service.UserService;
import com.lzy.chatmovie.util.Result;
import com.lzy.chatmovie.util.StatusCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 用户控制层
 * @author lzy
 * @since 2020.2.2
 */
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 添加用户
     * @param userVo 用户信息
     */
    @PostMapping(value = "/users")
    public void addUser(
            @ApiParam(value = "用户信息")
            @RequestBody UserVo userVo
            )
    {
        userService.addUser(userVo);
    }

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */

    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码")
    @PostMapping(value = "/sendmsg")
    public Result sendMsg(
            @ApiParam(value = "手机号码")
            @RequestBody String mobile
    )
    {
        System.out.println("手机号码是:"+mobile);
        userService.sendMsg(mobile);
        return new Result(true, StatusCode.OK,"发送短信成功！");
    }

    /**
     * 用户注册
     * @param registerDTO
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping(value = "/register")
    public Result register(
            @ApiParam(value = "用户注册")
           @RequestBody RegisterDTO registerDTO
            )
    {

        System.out.println("正在注册!");
       String checkCodeRedis= (String)redisTemplate.opsForValue().get("checkcode_"+registerDTO.getMobilePhone());
       String code=registerDTO.getCode();
       if(checkCodeRedis==null)
       {
           System.out.println("请获取手机验证码");
           return new Result(false,StatusCode.ERROR,"请获取手机验证码");
       }
       if(!checkCodeRedis.equals(code))
       {
           return new Result(false,StatusCode.ERROR,"验证码错误");
       }

       if(userService.findUserByMobilePhone(registerDTO.getMobilePhone())!=null)
           return new Result(false,StatusCode.REMOTEERROR,"手机号码已注册");
       if(userService.findUserByName(registerDTO.getName())!=null)
           return new Result(false,StatusCode.REMOTEERROR,"用户名已被注册");
       UserVo userVo=UserVo.builder()
               .name(registerDTO.getName())
               .password(registerDTO.getPassword())
               .mobilePhone(registerDTO.getMobilePhone()).build();
        userService.addUser(userVo);
        return new Result(true, StatusCode.OK,"注册成功！");
    }

    /**
     * 根据手机号查询用户
     * @param phoneNumber
     * @return
     */
    @ApiOperation(value = "根据手机号查询用户" ,notes = "根据手机号查询用户")
    @GetMapping("/userByPhone")
    public UserVo getUserByPhone(
            @ApiParam(value = "手机号")
            @RequestParam String phoneNumber)
    {
        User user =  userService.findUserByMobilePhone(phoneNumber);
        UserVo userVo = UserVo.builder().name(user.getName())
                .mobilePhone(user.getMobilePhone())
                .avatarURL(user.getAvatarUrl())
                .build();
        return userVo;
    }

    /**
     * 上传头像
     * @param zipFile
     * @return
     */
    @ApiOperation(value = "上传头像",notes="上传头像")
    @PostMapping("/avatar")
    public String upLoadAvatar(@RequestParam("uploadFile")
                                @ApiParam(value = "文件")
                                           MultipartFile zipFile
    )
    {
        return userService.upLoadAvatar(zipFile);
    }


}
