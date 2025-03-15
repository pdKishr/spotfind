package com.mvp.spotfind.repository;

import com.mvp.spotfind.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking,Long> {
    List<Parking> findAllByApproved(Boolean approved);
    List<Parking> findByOwnerId(Long ownerId);

   /* @Query(value = "SELECT * FROM parking_spots p " +
            "WHERE ST_DWithin( " +
            "ST_Transform(ST_SetSRID(ST_MakePoint(p.longitude, p.latitude), 4326), 3857), " +
            "ST_Transform(ST_SetSRID(ST_GeomFromText(:searchPoint, 4326), 4326), 3857), " +
            ":radius)",
            nativeQuery = true)
    List<Parking> findNearByParkingLots(@Param("searchPoint") String searchPointWKT , @Param("radius") double radius);*/
}
