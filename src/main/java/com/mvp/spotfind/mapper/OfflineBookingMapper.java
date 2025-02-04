package com.mvp.spotfind.mapper;

import com.mvp.spotfind.dto.OfflineBookingDto;
import com.mvp.spotfind.entity.OfflineBooking;

public class OfflineBookingMapper {
    public static OfflineBookingDto toDto(OfflineBooking offlineBooking){
        return new OfflineBookingDto(offlineBooking.getId(), offlineBooking.getMobileNumber(),
                offlineBooking.getVehicleType(), offlineBooking.getVehicleNumber(), offlineBooking.getParking().getParkingName()
        , offlineBooking.getParking().getAddress(), offlineBooking.getParking().getCity(), offlineBooking.getParking().getState(),
        offlineBooking.getBookedAt(),offlineBooking.getCheckedOutAt(),offlineBooking.getIsCheckOut());
    }
}
