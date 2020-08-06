package com.lzy.chatmovie.room.model.dto;

import lombok.*;

import java.time.LocalTime;

/**
 * <p>
 * 修改房间信息通信类
 * </p>
 *
 * @author zxd
 * @since 2020.2.24
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoomDTO {

    /*
     * 房间id
     */
    private int id;


    /*
     * 房间名称
     */
    private String name;

    /*
     * 房间介绍
     */
    private String detail;

    /*
     *视频路径
     */
    String videopath;

    /*
     *时间戳,视频开始的播放时间
     * 在更新videopath时设为当时时间（用来计算当前播放时间，实现同步播放）
     */
    LocalTime timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }
}
