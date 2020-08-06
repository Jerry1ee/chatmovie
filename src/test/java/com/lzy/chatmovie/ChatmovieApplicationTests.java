//package com.lzy.chatmovie;
//
//import com.lzy.chatmovie.room.dao.RoomRepository;
//import com.lzy.chatmovie.room.model.dto.CreateRoomDTO;
//import com.lzy.chatmovie.room.model.dto.UpdateRoomDTO;
//import com.lzy.chatmovie.room.service.RoomService;
//import com.lzy.chatmovie.user.model.po.User;
//import com.lzy.chatmovie.user.service.UserService;
//import jdk.nashorn.internal.ir.annotations.Ignore;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.util.Assert;
//
//import javax.transaction.Transactional;
//
//@SpringBootTest
//class ChatmovieApplicationTests {
//    @Autowired
//    RoomService roomService;
//    @Autowired
//    UserService userService;
//    @Autowired
//    RoomRepository roomRepository;
//
//    //    @Test
//    @Ignore
//    @Transactional
//    @Rollback
//    void contextLoads() {
//        User user = userService.findUserByName("zxd");
//        roomService.createRoom(
//                CreateRoomDTO.builder()
//                        .detail("detail")
//                        .name("room1")
//                        .build()
//        );
//        System.out.println(roomRepository.findAll());
//
//    }
//
//
//    @Test
//    @Transactional
//    @Rollback(value = true)
//    void addRoom_Test() {
//        User user = userService.findUserByName("zxd");
//        roomService.createRoom(
//                CreateRoomDTO.builder()
//                        .detail("detail")
//                        .name("room1")
//                        .build()
//        );
//        Assert.isTrue(roomRepository.findAll().size() == 2,
//                "房间个数为2");
//
//    }
//
//    @Test
//    @Transactional
//    @Rollback
//    void delRoom_Test() {
//        User user = userService.findUserByName("zxd");
//        roomService.delRoom(1);
//        Assert.isTrue(roomRepository.findAll().size() == 0,
//                "删除1后，房间个数为1");
//
//    }
//
//    @Test
//    @Transactional
//    @Rollback
//    void updateRoom_Test() {
//        User user = userService.findUserByName("zxd");
//        roomService.updateRoom(
//                UpdateRoomDTO.builder()
//                        .id(1)
//                        .detail("detail")
//                        .name("newName")
//                        .user(user)
//                        .build()
//        );
//        ;
//        Assert.isTrue(roomRepository.findById(1).get().getName().equals("newName"),
//                "更新后，房间名字为newName");
//    }
//
//
//
//}
