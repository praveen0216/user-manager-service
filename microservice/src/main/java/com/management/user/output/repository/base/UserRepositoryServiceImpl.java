package com.management.user.output.repository.base;

import com.management.user.input.mapper.UserMapper;
import com.management.user.model.User;
import com.management.user.model.UserFrequency;
import com.management.user.output.repository.entity.UserEntity;
import com.management.user.output.repository.service.UserRepositoryService;
import com.management.user.output.repository.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserRepositoryServiceImpl implements UserRepositoryService {

    private  final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserEntity create(User user) {
        UserEntity userEntity = userMapper.modelToEntity(user);
        return userRepository.save(userEntity);
    }


    @Override
    //ToDo
    public Optional<UserEntity> findByParticipantId(Long participantId) {
        return userRepository.findById(participantId);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long participantId) {
        return userRepository.findById(participantId);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserFrequency> findUserBidCounts() {
        return userRepository.findUserBidCounts();
    }
    @Override
    public List<UserFrequency> findUserAuctionCounts() {
        return userRepository.findUserAuctionCounts();
    }
}
