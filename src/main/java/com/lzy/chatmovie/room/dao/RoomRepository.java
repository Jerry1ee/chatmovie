package com.lzy.chatmovie.room.dao;

import com.lzy.chatmovie.room.model.po.Room;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 继承JpaRepository完成对数据库的操作
 *
 * @author zxd
 * @since 2020.2.22
 */

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Override
    List<Room> findAll();

    @Override
    List<Room> findAll(Sort sort);

    @Override
    List<Room> findAllById(Iterable<Integer> iterable);

    @Override
    <S extends Room> List<S> saveAll(Iterable<S> iterable);

    @Override
    void flush();

    @Override
    <S extends Room> S saveAndFlush(S s);

    @Override
    void deleteInBatch(Iterable<Room> iterable);

    @Override
    void deleteAllInBatch();

    @Override
    Room getOne(Integer integer);

    @Override
    <S extends Room> List<S> findAll(Example<S> example);

    @Override
    <S extends Room> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Page<Room> findAll(Pageable pageable);

    @Override
    <S extends Room> S save(S s);

    @Override
    Optional<Room> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Room room);

    @Override
    void deleteAll(Iterable<? extends Room> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends Room> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Room> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Room> long count(Example<S> example);

    @Override
    <S extends Room> boolean exists(Example<S> example);

    /**
     * @param rname 房间名称
     * @return List<Room>  房间列表
     */
    @ApiOperation(value = "根据房间名称寻找房间")
    List<Room> findRoomsByName(String rname);


}
