package com.lzy.chatmovie.room.controller;

import com.lzy.chatmovie.room.dao.RoomRepository;
import com.lzy.chatmovie.room.model.dto.CreateRoomDTO;
import com.lzy.chatmovie.room.model.dto.UpdateRoomDTO;
import com.lzy.chatmovie.room.model.dto.UpdateRoomVideoDTO;
import com.lzy.chatmovie.room.model.dto.UpdateVideoTimeDTO;
import com.lzy.chatmovie.room.model.po.Room;
import com.lzy.chatmovie.room.service.RoomService;
import com.lzy.chatmovie.util.Result;
import com.lzy.chatmovie.util.StatusCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * author :zxd
 * time: 2020.2.24
 * Room的CRUD
 */

@CrossOrigin
@RestController
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;


    /**
     * 获取所有rooms
     */
    @ApiOperation(value = "listRooms")
    @GetMapping(value = "/listRooms")
    public Result listRooms() {
        System.out.println("getAllrooms");
        List<Room> rooms = roomService.getAllRooms();
        HashMap<String, Room> data = new HashMap();
        return new Result(true, StatusCode.OK, "查找房间成功！", rooms);
    }

    /**
     * @param id
     */
    @ApiOperation(value = "查找房间byID")
    @GetMapping(value = "/getRoombyID/{id}")
    public Result getRoom(
            @ApiParam(value = "房间id")
            @PathVariable("id") int id) {
        System.out.println("Controller:getRoombyID" + id);
        Room room = roomService.getRoom(id);
        HashMap<String, Room> data = new HashMap();
        System.out.println(room.getName());
        data.put("room", room);
        if (room != null) {
            return new Result(true, StatusCode.OK, "查找房间成功！", room);
        }
        return new Result(false, StatusCode.ERROR, "查找房间失败！", room);
    }


    /**
     * @param createRoomDTO
     */
    @ApiOperation(value = "新建房间")
    @PostMapping(value = "/createRoom")
    public Result createRoom(
            @ApiParam(value = "房间信息(无id)")
            @RequestBody CreateRoomDTO createRoomDTO) {
        roomService.createRoom(createRoomDTO);
        return new Result(true, StatusCode.OK, "新建房间成功！");
    }

    /**
     * @param id //房间id
     */
    @ApiOperation(value = "删除房间byID")
    @GetMapping("/delRoom/{id}")
    public Result delRoom(
            @ApiParam(value = "房间id")
            @PathVariable("id") int id) {
        roomService.delRoom(id);
        return new Result(true, StatusCode.OK, "删除房间成功！");
    }

    /**
     * @param updateRoomDTO
     * @return
     */
    @ApiOperation(value = "更新房间信息")
    @PostMapping("/updateRoom")
    public Result updateRoom(
            @ApiParam(value = "房间信息(有id)")
            @RequestBody UpdateRoomDTO updateRoomDTO) {
        roomService.updateRoom(updateRoomDTO);
        return new Result(true, StatusCode.OK, "更新房间信息成功！");
    }

    /**
     * @param updateRoomVideoDTO
     * @return
     */
    @ApiOperation(value = "更新房间的视频")
    @PostMapping("/updateRoomVideo")
    public Result updateRoomVideo(
            @ApiParam(value = "房间信息(roomid,视频名称，视频url)")
            @RequestBody UpdateRoomVideoDTO updateRoomVideoDTO) {
        roomService.updateRoomVideo(updateRoomVideoDTO);
        return new Result(true, StatusCode.OK, "更新视频成功！");
    }


    /**
     * @param (roomid,视频时间)
     * @return
     */
    @ApiOperation(value = "设置房间视频时间节点")
    @GetMapping("/updateVideotime/{roomid}/{time}")
    public Result setVideoTime(
            @ApiParam(value = "(roomid,视频时间)")
            @PathVariable("roomid") int roomid,
            @PathVariable("time") int timestamp) {
        System.out.println("设置房间视频时间节点");
        UpdateVideoTimeDTO updateVideoTimeDTO = UpdateVideoTimeDTO.builder()
                .roomid(roomid)
                .timestamp(timestamp)
                .build();
        roomService.updateVideoTime(updateVideoTimeDTO);
        return new Result(true, StatusCode.OK, "设置节点成功！");
    }


    /**
     * @param id
     * @return 返回的data为int类型的时间
     */
    @ApiOperation(value = "同步视频播放时间")
    @GetMapping("/synVideo/{id}")
    public Result synVideo(
            @ApiParam(value = "房间id")
            @PathVariable("id") int id) {
        System.out.println("收到同步视频播放时间请求，id==" + id);
        int data = roomService.getVideoTime(id);
        return new Result(true, StatusCode.OK, "更新房间信息成功！", data);
    }

//    /**开始播放时，不设置0
//     * 开始播放视频，设置timestamp时间为0
//     *
//     * @param id
//     */
//    @ApiOperation(value = "播放视频")
//    @GetMapping(value = "/playVideo/{id}")
//    public Result playVideo(
//            @ApiParam(value = "房间id")
//            @PathVariable("id") int id) {
//        roomService.setTimestampbyId(id);
//        return new Result(true, StatusCode.OK, "播放视频成功！");
//    }

}
