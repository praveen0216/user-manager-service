package com.management.user.output.repository.service;

import com.management.user.model.User;
import com.management.user.output.repository.entity.UserEntity;

import java.util.Optional;

public interface UserRepositoryService {

    UserEntity create(User user);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByParticipantId(Long participantId);
}
