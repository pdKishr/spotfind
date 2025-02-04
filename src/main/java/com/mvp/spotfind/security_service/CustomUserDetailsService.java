package com.mvp.spotfind.security_service;

import com.mvp.spotfind.entity.Parking;
import com.mvp.spotfind.entity.User;
import com.mvp.spotfind.repository.ParkingRepository;
import com.mvp.spotfind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ParkingRepository parkingRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, ParkingRepository parkingRepository) {
        this.userRepository = userRepository;
        this.parkingRepository = parkingRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String idAsString) throws UsernameNotFoundException {
        Long id = Long.parseLong(idAsString);
;        User user = userRepository.findById(id).orElse(null);
        if(user != null) return user;

        Parking parking = parkingRepository.findById(id).orElse(null);
        if(parking != null) return parking;

        throw new UsernameNotFoundException("user or parking not found with id :"+ id);
    }

}
