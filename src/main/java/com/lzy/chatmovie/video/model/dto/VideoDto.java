package com.lzy.chatmovie.video.model.dto;

import lombok.*;

import java.io.Serializable;

/**
 * 视频通信对象
 * @author lzy
 * @since 2020.3.31
 * */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto implements Serializable {
    /**
     * 视频名
     */
    private String name;
    /**
     * 视频路径
     */
    private String path;
    /**
     * 创建者ID
     */
    private int c_user;
    /**
     * 创建者名称
     */
    private String c_username;
    /**
     * 介绍
     */
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getC_user() {
        return c_user;
    }

    public void setC_user(int c_user) {
        this.c_user = c_user;
    }

    public String getC_username() {
        return c_username;
    }

    public void setC_username(String c_username) {
        this.c_username = c_username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
