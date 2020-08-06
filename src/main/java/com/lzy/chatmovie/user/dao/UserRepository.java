package com.lzy.chatmovie.user.dao;

import com.lzy.chatmovie.user.model.po.User;
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
 * @since 2020.2.2
 */

public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * 根据mobilePhone 或 name 查询用户
     *
     * @param mobilePhone 用户mobilePhone
     * @param name 用户name
     * @return User
     */
    User findUserByMobilePhoneOrName(String mobilePhone,String name);

    /**
     * 根据mobilePhone查询用户
     *
     * @param mobilePhone 用户mobilePhone
     * @return User
     */
    User findUserByMobilePhone(String mobilePhone);


    /**
     * 根据name查询用户
     *
     * @param name 用户name
     * @return  User
     */
    User findUserByName(String name);

    @Override
    List<User> findAll();

    @Override
    List<User> findAll(Sort sort);

    @Override
    List<User> findAllById(Iterable<Long> iterable);

    @Override
    <S extends User> List<S> saveAll(Iterable<S> iterable);

    @Override
    void flush();

    @Override
    <S extends User> S saveAndFlush(S s);

    @Override
    void deleteInBatch(Iterable<User> iterable);

    @Override
    void deleteAllInBatch();

    @Override
    User getOne(Long aLong);

    @Override
    <S extends User> List<S> findAll(Example<S> example);

    @Override
    <S extends User> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    <S extends User> S save(S s);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(User user);

    @Override
    void deleteAll(Iterable<? extends User> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends User> Optional<S> findOne(Example<S> example);

    @Override
    <S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends User> long count(Example<S> example);

    @Override
    <S extends User> boolean exists(Example<S> example);
}
