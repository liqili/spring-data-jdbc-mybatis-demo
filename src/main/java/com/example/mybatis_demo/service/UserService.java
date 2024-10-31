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
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void save(User user) {
        userRepository.insert(user);
    }
}
