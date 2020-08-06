package com.lzy.chatmovie.video.model.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 视频持久层对象
 * @author lzy
 * @since 2020.3.31
 * */

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_video"
        ,uniqueConstraints = {@UniqueConstraint(columnNames = {"path"}),
        @UniqueConstraint(columnNames = {"name"})},
        indexes = {@Index(columnList = "path"),
                @Index(columnList = "name")})
public class Video implements Serializable {

    private static final long serialVersionUID = 6906097418517275811L;

    /**
     * 视频ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * 视频名
     */
    @Basic
    @Column(name = "name")
    private String name;
    /**
     * 视频路径
     */
    @Basic
    @Column(name = "path")
    private String path;
    /**
     * 创建者ID
     */
    @Basic
    @Column(name = "c_user")
    private int c_user;
    /**
     * 创建者名称
     */
    @Basic
    @Column(name = "c_username")
    private String c_username;

    /**
     * 介绍
     */
    @Basic
    @Column(name = "description")
    private String description;
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
    @Column(name="deleted")
    private boolean deleted;
    /* 表里都要有的字段*/

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
