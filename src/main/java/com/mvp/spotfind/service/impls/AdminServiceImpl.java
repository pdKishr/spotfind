package com.mvp.spotfind.service.impls;

import com.mvp.spotfind.Exceptionpack.UserNotFoundException;
import com.mvp.spotfind.dto.AdminDataTokenDto;
import com.mvp.spotfind.entity.Admin;
import com.mvp.spotfind.entity.Parking;
import com.mvp.spotfind.mapper.AdminMapper;
import com.mvp.spotfind.repository.AdminRepository;
import com.mvp.spotfind.repository.ParkingRepository;
import com.mvp.spotfind.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final ParkingRepository parkingRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(ParkingRepository parkingRepository, AdminRepository adminRepository) {
        this.parkingRepository = parkingRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public void approveParking(Long id) {
         Parking park = parkingRepository.findById(id).orElseThrow(()-> new UserNotFoundException("parking not found with this id "+id));
         park.setApproved(true);
         parkingRepository.save(park);
    }

    @Override
    public AdminDataTokenDto login(String mobileNumber, String password) {
        Admin admin = adminRepository.findByMobileNumberAndPassword(mobileNumber,password).orElseThrow(
                ()-> new UserNotFoundException("admin not found with mobile number :"+ mobileNumber)
        );
        return AdminMapper.adminDataTokenDto(admin);
    }

    @Override
    public void approveAllParking() {
        List<Parking> parkings = parkingRepository.findAll();
        parkings.forEach(parking -> parking.setApproved(true));
        parkingRepository.saveAll(parkings);
    }
}
