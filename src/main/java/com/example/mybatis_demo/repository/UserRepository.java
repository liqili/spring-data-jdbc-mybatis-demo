package com.example.mybatis_demo.repository;

import com.example.mybatis_demo.entity.User;
import com.example.mybatis_demo.support.mybatis.annotation.MyBatisQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kunkka on 31/10/24
 */
public interface UserRepository extends CrudRepository<User, Long> {

    @MyBatisQuery
    List<User> findAllUsers();

    @MyBatisQuery
    @Insert("INSERT INTO `user`(name, email) VALUES(#{name}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @MyBatisQuery
    @Select("SELECT * FROM `user` WHERE id = #{id}")
    User findByUserId(Long id);
}
