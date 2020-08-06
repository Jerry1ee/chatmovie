package com.lzy.chatmovie.room.model.dto;

import lombok.*;

/**
 * <p>
 * 修改房间中视频信息通信类
 * </p>
 *
 * @author zxd
 * @since 2020.4.6
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoomVideoDTO {

    /*
     * 房间id
     */
    private int roomid;

    /*
     *视频路径
     */
    String videopath;

    /*
     *图片路径
     */
    String picpath;


    /*
     *视频名称
     */
    String videoname;

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }
}
