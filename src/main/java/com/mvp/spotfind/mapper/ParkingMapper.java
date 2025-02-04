package com.mvp.spotfind.mapper;
import com.mvp.spotfind.dto.ParkingDto;
import com.mvp.spotfind.dto.ParkingTokenDataDto;
import com.mvp.spotfind.entity.Parking;

public class ParkingMapper {

    public static Parking toEntity(ParkingDto dto){

        return new Parking(dto.getEmail(),dto.getPassword(),dto.getMobileNumber(),dto.getParkingName(),dto.getOwner(),
                dto.getLocation(),dto.getAddress(),dto.getCity(),dto.getState(),dto.getIsBikeParkingAvailable(),dto.getIsCarParkingAvailable(),
                dto.getIsAvailableFor24Hours(),dto.getNoOfBikeSpots(),dto.getNoOfCarSpots(),dto.getBikeCharge(),dto.getCarCharge(),
                dto.getOpenTime(),dto.getCloseTime()
        );

    }

    public static ParkingDto toDto(Parking p){

        return  new ParkingDto(p.getEmail(),p.getPassword(),p.getMobileNumber(),p.getParkingName(),
                p.getOwner(),p.getLocation(),p.getAddress(),p.getCity(),p.getState(),p.getIsBikeParkingAvailable(),p.getNoOfBikeSpots(),
                p.getBikeCharge(),p.getIsCarParkingAvailable(),p.getNoOfCarSpots(),p.getCarCharge(),
                p.getIsAvailableFor24Hours(),p.getOpenTime(),p.getCloseTime()
        );

    }

    public static ParkingTokenDataDto toParkingTokenDataDto(Parking p){
        return new ParkingTokenDataDto(p.getId(),p.getRole());
    }

}