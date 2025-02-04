package com.mvp.spotfind.service;

import com.mvp.spotfind.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto dto);
    Long login(String mobileNumber,String password);
    UserDto updateUser(Long id ,UserDto dto);
}
