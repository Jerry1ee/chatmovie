package com.lzy.chatmovie.room.service;

import com.lzy.chatmovie.room.dao.RoomRepository;
import com.lzy.chatmovie.room.model.dto.CreateRoomDTO;
import com.lzy.chatmovie.room.model.dto.UpdateRoomDTO;
import com.lzy.chatmovie.room.model.dto.UpdateRoomVideoDTO;
import com.lzy.chatmovie.room.model.dto.UpdateVideoTimeDTO;
import com.lzy.chatmovie.room.model.po.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    /*
     * 创建房间
     */
    public void createRoom(CreateRoomDTO createRoomDTO) {
        roomRepository.saveAndFlush(
                Room.builder()
                        .name(createRoomDTO.getName())
                        .detail(createRoomDTO.getDetail())
                        .build()
        );
    }

    /*
     * 删除房间ByID
     * */
    public void delRoom(int roomid) {
        roomRepository.deleteById(roomid);
    }


    /*
     * 修改房间信息
     */
    public Room updateRoom(UpdateRoomDTO updateRoomDTO) {
        Room room = roomRepository.findById(updateRoomDTO.getId()).get();
        room.setDetail(updateRoomDTO.getDetail());
        room.setName(updateRoomDTO.getName());
        roomRepository.saveAndFlush(room);
        return room;
    }


    /*
     * 修改房间的视频，需要更新时间
     */
    public Room updateRoomVideo(UpdateRoomVideoDTO updateRoomVideoDTO) {
        Room room = roomRepository.findById(updateRoomVideoDTO.getRoomid()).get();
        room.setVideopath(updateRoomVideoDTO.getVideopath());
        room.setVideoname(updateRoomVideoDTO.getVideoname());
        room.setPicpath(updateRoomVideoDTO.getPicpath());
        room.setTimestamp(0);
        roomRepository.saveAndFlush(room);
        return room;
    }

    /*
     * 获取房间ByID
     * */
    public Room getRoom(int roomid) {
        Room room = null;
        try {
            room = roomRepository.findById(roomid).get();
        } catch (Exception e) {
            return null;
        }
        System.out.println("getroom" + roomid + "!" + room);
        return room;
    }


    /*
     * 获取所有房间
     * */
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    /**
     * 获取视频播放时间点（当前时间-视频开始播放时间）
     *
     * @return 视频播放时间点
     */
    public int getVideoTime(int id) {
        Room room = roomRepository.findById(id).get();
//        int hou = LocalTime.now().getHour() - room.getTimestamp().getHour();
//        int mins = LocalTime.now().getMinute() - room.getTimestamp().getMinute();
//        int sec = LocalTime.now().getSecond() - room.getTimestamp().getSecond();
//        int ans = hou * 3600 + mins * 60 + sec;
        System.out.println(room.getTimestamp());
        return room.getTimestamp();
    }

    /**
     * 开始播放视频
     *
     * @return 重设timestamp
     */
    public void setTimestampbyId(int roomID) {
        Room room = roomRepository.findById(roomID).get();
        room.setTimestamp(0);
        roomRepository.saveAndFlush(room);
    }

    /*
     * 修改房间的视频播放时间
     */
    public void updateVideoTime(UpdateVideoTimeDTO updateVideoTimeDTO) {
        Room room = roomRepository.findById(updateVideoTimeDTO.getRoomid()).get();
        room.setTimestamp(updateVideoTimeDTO.getTimestamp());
        roomRepository.saveAndFlush(room);
    }


    public static void main(String[] args) {

    }

}
