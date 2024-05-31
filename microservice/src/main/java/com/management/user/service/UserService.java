package com.management.user.service;

import com.management.user.input.mapper.UserMapper;
import com.management.user.model.User;
import com.management.user.output.repository.service.UserRepositoryService;
import com.management.user.output.repository.spi.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private  final UserRepositoryService userRepositoryService;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, UserRepository userRepository, UserRepositoryService userRepositoryService, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepositoryService = userRepositoryService;
        this.passwordEncoder = passwordEncoder;
    }

   /* public UserModel createUser(UserModel userModel) {
        User user = UserMapper.INSTANCE.userModelToUser(userModel);
        user = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserModel(user);
    }

    public List<UserModel> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper.INSTANCE::userToUserModel)
                .collect(Collectors.toList());
    }*/


    public User register(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.entityToModel(userRepositoryService.create(user));
    }

    public User findByUsername(String username) {
        return userMapper.entityToModel(userRepositoryService.findByUsername(username).orElse(null));
    }
}
