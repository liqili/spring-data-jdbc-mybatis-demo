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
    @Select("SELECT * FROM users")
    List<User> findAll();

    @Insert("INSERT INTO users(name, email) VALUES(#{name}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(Long id);
}

