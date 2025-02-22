package com.mvp.spotfind.service;

import com.mvp.spotfind.dto.AdminParkingViewDto;
import com.mvp.spotfind.dto.ParkingDto;

import java.util.List;

public interface ParkingService {
    ParkingDto createParking(ParkingDto dto,Long owner_id);
    ParkingDto  updateParking(Long id , ParkingDto dto);
    ParkingDto  getParkingById(Long id);
    List<AdminParkingViewDto> getAllParking();
    List<ParkingDto> getAllApprovedParking();
    List<ParkingDto> getAllParkingById(Long id);
}
