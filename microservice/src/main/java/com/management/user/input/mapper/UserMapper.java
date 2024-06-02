package com.management.user.input.mapper;

import com.management.user.model.User;
import com.management.user.model.UserDto;
import com.management.user.output.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity modelToEntity(User user);

    User entityToModel(UserEntity userModel);

    List<User> entitiesToModel(List<UserEntity> userEntities);
    List<UserEntity> modelsToEntities(List<User> bids);

    default UserDto modelToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
