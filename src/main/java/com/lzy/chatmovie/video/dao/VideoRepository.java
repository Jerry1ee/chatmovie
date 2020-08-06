package com.lzy.chatmovie.video.dao;

import com.lzy.chatmovie.video.model.po.Video;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 继承JpaRepository完成对数据库的操作
 * @author lzy
 * @since 2020.3.31
 */
public interface VideoRepository extends JpaRepository<Video,Long> {

    /**
     * 根据名称找查找视频
     * @param name 视频name
     * @return VideoController
     */
    public Video findByName(String name);

    public List<Video> findAllByNameLike(String name);

    @Override
    List<Video> findAll();

    @Override
    List<Video> findAll(Sort sort);

    @Override
    List<Video> findAllById(Iterable<Long> iterable);

    @Override
    <S extends Video> List<S> saveAll(Iterable<S> iterable);

    @Override
    void flush();

    @Override
    <S extends Video> S saveAndFlush(S s);

    @Override
    void deleteInBatch(Iterable<Video> iterable);

    @Override
    void deleteAllInBatch();

    @Override
    Video getOne(Long aLong);

    @Override
    <S extends Video> List<S> findAll(Example<S> example);

    @Override
    <S extends Video> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Page<Video> findAll(Pageable pageable);

    @Override
    <S extends Video> S save(S s);

    @Override
    Optional<Video> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Video video);

    @Override
    void deleteAll(Iterable<? extends Video> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends Video> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Video> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Video> long count(Example<S> example);

    @Override
    <S extends Video> boolean exists(Example<S> example);


}
