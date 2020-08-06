package com.lzy.chatmovie.room.model.dto;

import lombok.*;

/**
 * <p>
 * 创建房间通信类
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
public class CreateRoomDTO {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * 房间名称
     */
    private String name;

    /*
     * 房间介绍
     */
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
