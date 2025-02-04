package com.mvp.spotfind.service;

import com.mvp.spotfind.dto.BookingDto;

import java.util.List;

public interface BookingService {
    BookingDto bookBikeTicket(Long userId, Long parkingId , String vehicleNumber);
    BookingDto bookCarTicket(Long userId, Long parkingId , String vehicleNumber);
    List<BookingDto> getBookingByUser(Long userId);
    List<BookingDto> getBookingByParking(Long ParkingId);
    void checkout(Long bookingId);
}
