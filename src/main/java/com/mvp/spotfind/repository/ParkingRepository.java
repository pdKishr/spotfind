package com.mvp.spotfind.repository;

import com.mvp.spotfind.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking,Long> {
    List<Parking> findAllByApproved(Boolean approved);
    List<Parking> findByOwnerId(Long ownerId);
    List<Parking> findByLocationAndCityAndApprovedAndIsBikeParkingAvailable (String location , String city, Boolean approved , Boolean isBikeParkingAvailable);
    List<Parking> findByLocationAndCityAndApprovedAndIsCarParkingAvailable  (String location , String city, Boolean approved , Boolean isCarParkingAvailable);

    @Query("SELECT P FROM Parking P WHERE P.approved = true AND " +
            "( LOWER(P.city) LIKE LOWER(CONCAT('%', :keyword1, '%')) OR " +
            "  LOWER(P.location) LIKE LOWER(CONCAT('%', :keyword1, '%')) OR " +
            "  LOWER(P.address) LIKE LOWER(CONCAT('%', :keyword1, '%')) OR " +
            "  LOWER(P.city) LIKE LOWER(CONCAT('%', :keyword2, '%')) OR " +
            "  LOWER(P.location) LIKE LOWER(CONCAT('%', :keyword2, '%')) OR " +
            "  LOWER(P.address) LIKE LOWER(CONCAT('%', :keyword2, '%'))) AND " +
            "((:vehicle = 'car' AND P.isCarParkingAvailable = true) OR " +
            " (:vehicle = 'bike' AND P.isBikeParkingAvailable = true))")
    List<Parking> searchParking(@Param("keyword1") String keyword1, @Param("keyword2") String keyword2 , @Param("vehicle") String vehicle);
}
