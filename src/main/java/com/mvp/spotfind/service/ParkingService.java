package com.mvp.spotfind.service;

import com.mvp.spotfind.dto.ParkingDto;

public interface ParkingService {
    ParkingDto createParking(ParkingDto dto);
    Long login(String mobileNumber, String password);
    ParkingDto  updateParking(Long id , ParkingDto dto);
    ParkingDto  getParkingById(Long id);
}
