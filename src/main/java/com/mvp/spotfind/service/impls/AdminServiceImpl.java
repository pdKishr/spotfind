package com.mvp.spotfind.service.impls;

import com.mvp.spotfind.Exceptionpack.UserNotFoundException;
import com.mvp.spotfind.entity.Parking;
import com.mvp.spotfind.repository.ParkingRepository;
import com.mvp.spotfind.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    ParkingRepository parkingRepository;

    @Autowired
    public AdminServiceImpl(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public void approveParking(Long id) {
         Parking park = parkingRepository.findById(id).orElseThrow(()-> new UserNotFoundException("parking not found with this id "+id));
         park.setApproved(true);
         parkingRepository.save(park);
    }
}
