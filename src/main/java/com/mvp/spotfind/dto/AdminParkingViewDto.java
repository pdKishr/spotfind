package com.mvp.spotfind.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class AdminParkingViewDto extends ParkingDto{
    private Boolean approved;

    public AdminParkingViewDto(String email, String password, String mobileNumber, String parkingName, String owner, String location, String address, String city, String state, Boolean isBikeParkingAvailable, Integer noOfBikeSpots, Integer bikeCharge, Boolean isCarParkingAvailable, Integer noOfCarSpots, Integer carCharge, Boolean isAvailableFor24Hours, String openTime, String closeTime, Boolean approved) {
        super(email, password, mobileNumber, parkingName, owner, location, address, city, state, isBikeParkingAvailable, noOfBikeSpots, bikeCharge, isCarParkingAvailable, noOfCarSpots, carCharge, isAvailableFor24Hours, openTime, closeTime);
        this.approved = approved;
    }
}
