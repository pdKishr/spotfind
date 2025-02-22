package com.mvp.spotfind.mapper;

import com.mvp.spotfind.dto.UserDto;
import com.mvp.spotfind.dto.UserProfileUpdateDto;
import com.mvp.spotfind.dto.UserTokenDataDto;
import com.mvp.spotfind.entity.User;

public class UserMapper {
    public static User toEntity(UserDto dto){
        return new User(dto.getName(),dto.getEmail(),dto.getMobileNumber(),dto.getPassword());
    }

    public static UserDto toDto(User u){
        return new UserDto(u.getName(),u.getMobileNumber(),u.getEmail(),u.getPassword());
    }

    public static UserTokenDataDto toUserTokenDataDto(User user){
        return new UserTokenDataDto(user.getId(),user.getRole());
    }

}
