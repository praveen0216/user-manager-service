package com.management.user.service;

import com.management.user.input.mapper.UserMapper;
import com.management.user.model.User;
import com.management.user.model.UserFrequency;
import com.management.user.output.repository.service.UserRepositoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private  final UserRepositoryService userRepositoryService;


    public UserService(UserMapper userMapper, UserRepositoryService userRepositoryService) {
        this.userMapper = userMapper;
        this.userRepositoryService = userRepositoryService;
    }

    public User register(User user) {
        return userMapper.entityToModel(userRepositoryService.create(user));

    }

    public User findByEmail(String email) {
        return userMapper.entityToModel(userRepositoryService.findByEmail(email).orElse(null));
    }

    public List<User> getAllUsers() {
        return  userMapper.entitiesToModel(userRepositoryService.findAll());
    }

    public List<UserFrequency> getUserBidCountDTOS() {
        return userRepositoryService.findUserBidCounts();
    }
}
