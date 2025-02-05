package com.mvp.spotfind.mapper;

import com.mvp.spotfind.dto.AdminDataTokenDto;
import com.mvp.spotfind.entity.Admin;

public class AdminMapper {
    public static AdminDataTokenDto adminDataTokenDto(Admin admin){
        return new AdminDataTokenDto(admin.getId(),admin.getRole());
    }
}
