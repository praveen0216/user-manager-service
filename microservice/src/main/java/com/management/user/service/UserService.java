package com.management.user.service;

import com.management.user.input.mapper.UserMapper;
import com.management.user.model.User;
import com.management.user.model.UserFrequency;
import com.management.user.output.repository.entity.UserEntity;
import com.management.user.output.repository.service.UserRepositoryService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;
    private  final UserRepositoryService userRepositoryService;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserMapper userMapper, UserRepositoryService userRepositoryService, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepositoryService = userRepositoryService;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return userMapper.entityToModel(userRepositoryService.create(user));

    }

    public User findByEmail(String email) {
        Optional<UserEntity> userEntity = userRepositoryService.findByEmail(email);
        return userMapper.entityToModel(userEntity.get());
    }

    public List<User> getAllUsers() {
        return  userMapper.entitiesToModel(userRepositoryService.findAll());
    }

    public List<UserFrequency> getUserBidCountDTOS() {
        return userRepositoryService.findUserBidCounts();
    }
}
