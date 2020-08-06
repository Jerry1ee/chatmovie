package com.lzy.chatmovie.user.model.vo;

import lombok.*;

/**
 * <p>
 * 用户控制层对象
 * </p>
 *
 * @author Jerry1ee
 * @since 2020.2.3
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户手机号码
     */
    private String mobilePhone;
    /**
     * 用户头像URL
     */
    private String avatarURL;
    /**
     * 密码
     */
    private String password;

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

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
