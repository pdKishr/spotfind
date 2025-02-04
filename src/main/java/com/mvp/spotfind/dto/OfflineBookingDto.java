package com.mvp.spotfind.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OfflineBookingDto {
    private Long   id;
    private String mobileNumber;
    private String parkingName;
    private String address;
    private String parkingAddress;
    private String city;
    private String state;
    private LocalDateTime bookedAt;
    private LocalDateTime checkedOutAt;
    private String vehicleType;
    private String vehicleNumber;
    private LocalDateTime BookedAt;
    private Boolean isCheckOut;
    private LocalDateTime CheckedAtOut;

    public OfflineBookingDto(Long id, String mobileNumber, String vehicleType, String vehicleNumber, String parkingName, String address, String city, String state, LocalDateTime bookedAt, LocalDateTime checkedOutAt, Boolean isCheckOut) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.parkingName = parkingName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.bookedAt = bookedAt;
        this.checkedOutAt = checkedOutAt;
        this.isCheckOut = isCheckOut;
    }
}