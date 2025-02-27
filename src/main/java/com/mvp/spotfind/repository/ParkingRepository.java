package com.mvp.spotfind.repository;

import com.mvp.spotfind.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking,Long> {
    List<Parking> findAllByApproved(Boolean approved);
    List<Parking> findByOwnerId(Long ownerId);
    List<Parking> findByLocationAndCityAndApprovedAndIsBikeParkingAvailable (String location , String city, Boolean approved , Boolean isBikeParkingAvailable);
    List<Parking> findByLocationAndCityAndApprovedAndIsCarParkingAvailable  (String location , String city, Boolean approved , Boolean isCarParkingAvailable);

}
