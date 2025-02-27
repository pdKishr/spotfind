package com.mvp.spotfind.service;

import com.mvp.spotfind.dto.AdminDataTokenDto;

public interface AdminService {
    void approveParking(Long id);
    AdminDataTokenDto login(String mobileNumber,String password);
    void approveAllParking();
}
