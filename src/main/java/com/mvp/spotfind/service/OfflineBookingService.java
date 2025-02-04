package com.mvp.spotfind.service;

import com.mvp.spotfind.dto.OfflineBookingDto;

import java.util.List;

public interface OfflineBookingService {
    OfflineBookingDto bookBikeTicketOffline(Long parkingId, String mobileNumber,String vehicleNumber);
    OfflineBookingDto bookCarTicketOffline (Long parkingId, String mobileNumber, String vehicleNumber);
    List<OfflineBookingDto> getTicketByParking(Long parkingId);
    void checkout(Long bookingId);
}