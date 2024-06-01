package com.management.user.input.mapper;

import com.management.user.model.User;
import com.management.user.output.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity modelToEntity(User user);

    User entityToModel(UserEntity userModel);

    List<User> entitiesToModel(List<UserEntity> bids);
    List<UserEntity> modelsToEntities(List<User> bids);
}
