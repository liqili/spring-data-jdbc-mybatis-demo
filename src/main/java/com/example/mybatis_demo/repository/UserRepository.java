package com.example.mybatis_demo.repository;

import com.example.mybatis_demo.entity.User;
import com.example.mybatis_demo.mapper.UserMapper;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kunkka on 31/10/24
 */
public interface UserRepository extends CrudRepository<User, Long>, UserMapper {
}
