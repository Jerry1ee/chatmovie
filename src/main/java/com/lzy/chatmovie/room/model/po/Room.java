package com.lzy.chatmovie.room.model.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 用户持久层对象
 *
 * @author zxd
 * @since 2020.2.22
 */


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_room")
public class Room implements Serializable {
    private static final long serialVersionUID = 6906097418517275872L;

    /*
     *房间id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    /*
     *  房间名称
     */
    @Basic
    @Column(name = "name")
    String name;


//    /*  取消级联关系
//     *房主id
//     */
//    @ManyToOne
//    User user;

    /*
     *房间介绍
     */
    @Basic
    @Column(name = "detail")
    String detail;

    /*
     *视频路径
     */
    @Basic
    @Column(name = "videopath")
    String videopath;


    /*
     *视频名称
     */
    @Basic
    @Column(name = "videoname")
    String videoname;

    /*
     *时间戳,视频开始的播放时间（用来计算当前播放时间，实现同步播放）
     */
    @Basic
    @Column(name = "timestamp")
    int timestamp;

    /*
     *图片路径
     */
    @Basic
    @Column(name = "picpath")
    String picpath;


    /* 表里都要有的字段*/
    /**
     * 创建时间
     */
    @Basic
    @Column(name = "ctime")
    private LocalDateTime ctime;
    /**
     * 修改时间
     */
    @Basic
    @Column(name = "mtime")
    private LocalDateTime mtime;
    /**
     * 删除标记
     */
    @Basic
    @Column(name = "deleted")
    private boolean deleted;
    /* 表里都要有的字段*/

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    public LocalDateTime getMtime() {
        return mtime;
    }

    public void setMtime(LocalDateTime mtime) {
        this.mtime = mtime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
