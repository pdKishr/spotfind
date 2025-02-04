package com.mvp.spotfind.mapper;

import com.mvp.spotfind.dto.BookingDto;
import com.mvp.spotfind.entity.Booking;

public class BookingMapper {
    public static BookingDto toDto(Booking booking){
        return new BookingDto(booking.getId(),booking.getUser().getName(),booking.getParking().getParkingName(),
           booking.getParking().getAddress(), booking.getParking().getCity(),booking.getParking().getState(),
                booking.getVehicleType(), booking.getVehicleNumber(),
                booking.getBookedAt(),booking.getIsCheckOut(),booking.getCheckedOutAt()
        );
    }
}