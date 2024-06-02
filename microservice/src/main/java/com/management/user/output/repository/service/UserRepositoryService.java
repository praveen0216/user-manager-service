package com.management.user.output.repository.service;

import com.management.user.model.User;
import com.management.user.model.UserFrequency;
import com.management.user.output.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryService {

    UserEntity create(User user);

    Optional<UserEntity> findByParticipantId(Long participantId);

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long participantId);

    Optional<UserEntity> findByEmail(String email);

    List<UserFrequency> findUserBidCounts();

    List<UserFrequency> findUserAuctionCounts();
}
