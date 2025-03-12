package com.mvp.spotfind.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class AdminParkingViewDto extends ParkingDto{
    private Boolean approved;
    private UserDto owner;

    public AdminParkingViewDto(Long id ,String email, String mobileNumber, String parkingName, String location, String address, String city, String state, Boolean isBikeParkingAvailable, Integer noOfBikeSpots, Integer bikeCharge, Boolean isCarParkingAvailable, Integer noOfCarSpots, Integer carCharge, Boolean isAvailableFor24Hours, String openTime, String closeTime, Boolean approved, UserDto owner, Integer availableBikeSpots , Integer availableCarSpots, Double latitude,Double longitude,String pincode) {
        super(id,email, mobileNumber, parkingName, location, address, city, state, isBikeParkingAvailable, noOfBikeSpots, bikeCharge, isCarParkingAvailable, noOfCarSpots, carCharge, isAvailableFor24Hours, openTime, closeTime, availableBikeSpots ,availableCarSpots,latitude,longitude,pincode);
        this.approved = approved;
        this.owner = owner;

    }
}
