package com.mvp.spotfind.repository;

import com.mvp.spotfind.entity.OfflineBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OfflineBookingRepository extends JpaRepository<OfflineBooking, Long> {
    List<OfflineBooking> findByParkingId(Long parkingId) ;
}
