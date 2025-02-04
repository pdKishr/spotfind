package com.mvp.spotfind.repository;

import com.mvp.spotfind.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking,Long> {
    Optional<Parking> findByMobileNumberAndPassword(String mobileNumber,String password);
}
