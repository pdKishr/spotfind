package com.mvp.spotfind.service;

import com.mvp.spotfind.dto.AdminParkingViewDto;
import com.mvp.spotfind.dto.ParkingDto;
import com.mvp.spotfind.dto.ParkingTokenDataDto;

import java.util.List;

public interface ParkingService {
    ParkingDto createParking(ParkingDto dto);
    ParkingTokenDataDto login(String mobileNumber, String password);
    ParkingDto  updateParking(Long id , ParkingDto dto);
    ParkingDto  getParkingById(Long id);
    List<AdminParkingViewDto> getAllParking();
    List<ParkingDto> getAllApprovedParking();
}
