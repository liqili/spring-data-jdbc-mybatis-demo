package com.example.mybatis_demo.mapper;

/**
 * Created by kunkka on 31/10/24
 */

import com.example.mybatis_demo.entity.User;
import com.example.mybatis_demo.support.mybatis.annotation.MyBatisMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@MyBatisMapper
public interface UserMapper {
//    @Select("SELECT * FROM `user`")
    List<User> findAllUsers();

    @Insert("INSERT INTO `user`(name, email) VALUES(#{name}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT * FROM `user` WHERE id = #{id}")
    User findByUserId(Long id);
}

