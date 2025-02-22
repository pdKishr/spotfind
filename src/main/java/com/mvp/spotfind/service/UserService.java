package com.mvp.spotfind.service;

import com.mvp.spotfind.dto.UserDto;
import com.mvp.spotfind.dto.UserProfileUpdateDto;
import com.mvp.spotfind.dto.UserTokenDataDto;

public interface UserService {
    UserDto createUser(UserDto dto);
    UserTokenDataDto login(String mobileNumber, String password);
    UserDto getUser(Long id);
    UserDto updateUser(Long id , UserProfileUpdateDto dto);
}
