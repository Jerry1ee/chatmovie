package com.lzy.chatmovie.user.service;


import com.lzy.chatmovie.user.dao.UserRepository;
import com.lzy.chatmovie.user.model.po.User;
import com.lzy.chatmovie.user.model.vo.UserVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户业务类
 * @author lzy
 * @since 2020.2.2
 */

@Configuration
@Service
public class UserService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    /**
     * 注册
     * @param userVo
     */
    public void addUser(UserVo userVo)
    {
            User user = User.builder()
                    .name(userVo.getName())
                    .password(encoder.encode(userVo.getPassword()))
                    .mobilePhone(userVo.getMobilePhone())
                    .avatarUrl("")
                    .ctime(LocalDateTime.now())
                    .mtime(LocalDateTime.now())
                    .deleted(false)
                    .build();
        userRepository.saveAndFlush(user);
    }

    /**
     * 登录功能
     * @param mobilePhone
     * @param password
     * @return
     */
    public User login(String mobilePhone,String password)
    {
        User user = userRepository.findUserByMobilePhone(mobilePhone);
        if(user!=null && encoder.matches(password,user.getPassword()))
        {
            return user;
        }
        return null;
    }

    /**
     * 通过电话号查找用户
     * @param mobilePhone
     * @return
     */
    public User findUserByMobilePhone(String mobilePhone)
    {
        User existUser =  userRepository.findUserByMobilePhone(mobilePhone);
        return existUser ;
    }
    /**
     * 通过用户名查找用户
     * @param name
     * @return
     */
    public User findUserByName(String name)
    {
        User existUser =  userRepository.findUserByName(name);
        return existUser ;
    }

    /**
     * 发送短信
     * @param mobile 手机号
     */
    public void sendMsg(String mobile)
    {
        //生成六位随机数
        String checkCode = RandomStringUtils.randomNumeric(6);
        Map<String,String>  map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("checkcode",checkCode);

        //向缓存中存一份
        redisTemplate.opsForValue().set("checkcode_"+mobile,checkCode,15, TimeUnit.MINUTES);
        //向用户发送一份
        rabbitTemplate.convertAndSend("msg",map);
        //打印一份
        System.out.println("验证码为:"+ checkCode);

    }

    public String upLoadAvatar(MultipartFile zipFile)
    {
        String targetFilePath = "D:\\";
        //获得 .之后的内容 即 JPG
        int lastIndexOf = zipFile.getOriginalFilename().lastIndexOf(".");
        String fileName = UUID.randomUUID().toString().replace("-", "")+zipFile.getOriginalFilename().substring(lastIndexOf);
        System.out.println(fileName);
        File targetFile = new File(targetFilePath + File.separator + fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targetFile);
            IOUtils.copy(zipFile.getInputStream(), fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {

            }
        }
        return targetFile.getPath();

    }

}
