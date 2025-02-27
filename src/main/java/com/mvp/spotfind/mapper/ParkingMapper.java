package com.mvp.spotfind.mapper;

import com.mvp.spotfind.dto.AdminParkingViewDto;
import com.mvp.spotfind.dto.ParkingDto;
import com.mvp.spotfind.entity.Parking;
import com.mvp.spotfind.entity.User;

public class ParkingMapper {

    public static Parking toEntity(ParkingDto dto, User owner){

        return new Parking(dto.getEmail(),dto.getMobileNumber(),dto.getParkingName(),owner,
                dto.getLocation(),dto.getAddress(),dto.getCity(),dto.getState(),dto.getIsBikeParkingAvailable(),dto.getIsCarParkingAvailable(),
                dto.getIsAvailableFor24Hours(),dto.getNoOfBikeSpots(),dto.getNoOfCarSpots(),dto.getBikeCharge(),dto.getCarCharge(),
                dto.getOpenTime(),dto.getCloseTime()
        );

    }

    public static ParkingDto toDto(Parking p){

        return  new ParkingDto (p.getId(),p.getEmail(),p.getMobileNumber(),p.getParkingName(),
                p.getLocation(),p.getAddress(),p.getCity(),p.getState(),p.getIsBikeParkingAvailable(),p.getNoOfBikeSpots(),
                p.getBikeCharge(),p.getIsCarParkingAvailable(),p.getNoOfCarSpots(),p.getCarCharge(),
                p.getIsAvailableFor24Hours(),p.getOpenTime(),p.getCloseTime() ,p.getAvailableBikeSpots() ,
                p.getAvailableCarSpots()
        );

    }

    public static AdminParkingViewDto toAdminParkingViewDto(Parking p){
        return  new AdminParkingViewDto(p.getId(),p.getEmail(),p.getMobileNumber(),p.getParkingName(),
                p.getLocation(),p.getAddress(),p.getCity(),p.getState(),p.getIsBikeParkingAvailable(),p.getNoOfBikeSpots(),
                p.getBikeCharge(),p.getIsCarParkingAvailable(),p.getNoOfCarSpots(),p.getCarCharge(),
                p.getIsAvailableFor24Hours(),p.getOpenTime(),p.getCloseTime() , p.getApproved(),UserMapper.toDto(p.getOwner()) ,
                p.getAvailableBikeSpots() , p.getAvailableCarSpots()
        );
    }

}