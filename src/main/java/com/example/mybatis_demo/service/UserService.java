package com.example.mybatis_demo.service;

/**
 * Created by kunkka on 31/10/24
 */

import com.example.mybatis_demo.entity.User;
import com.example.mybatis_demo.repository.UserRepository;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Table(name = "users")
public class UserService {
    private final UserRepository userMapper;

    public UserService(UserRepository userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public User findById(Long id) {
        return userMapper.findById(id);
    }

    public void save(User user) {
        userMapper.insert(user);
    }
}
