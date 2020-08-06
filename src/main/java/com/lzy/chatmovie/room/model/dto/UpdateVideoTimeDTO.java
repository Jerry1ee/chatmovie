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
public class UpdateVideoTimeDTO {

    /*
     * 房间id
     */
    private int roomid;

    /*
     *时间戳,视频开始的播放时间（用来计算当前播放时间，实现同步播放）
     */
    int timestamp;

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
