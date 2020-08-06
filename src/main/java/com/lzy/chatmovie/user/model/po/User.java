package com.lzy.chatmovie.user.model.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户持久层对象
 * @author lzy
 * @since 2020.2.2
 */

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user"
,uniqueConstraints = {@UniqueConstraint(columnNames = {"mobile_phone"}),
        @UniqueConstraint(columnNames = {"name"})},
        indexes = {@Index(columnList = "mobile_phone"),
                @Index(columnList = "name")})
public class User implements Serializable {

    private static final long serialVersionUID = 6906097418517275871L;

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * 用户名
     */
    @Basic
    @Column(name = "name")
    private String name;
    /**
     * 用户手机号
     */
    @Basic
    @Column(name = "mobile_phone")
    private String mobilePhone;
    /**
     * 密码
     */
    @Basic
    @Column(name = "password")
    private String password;
    /**
     * 用户头像地址
     */
    @Basic
    @Column(name = "avatar_url")
    private String avatarUrl;


//    /**  暂时取消级联关系
//     * 用户关联房间
//     * created by zxd 2/22
//     * 设置关系
//     * 设置级联删除
//     */
//    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
//    @Column(name = "rooms")
//    private Set<Room> rooms;


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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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
