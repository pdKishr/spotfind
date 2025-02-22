package com.mvp.spotfind.service.impls;

import com.mvp.spotfind.entity.Admin;
import com.mvp.spotfind.entity.Parking;
import com.mvp.spotfind.entity.User;
import com.mvp.spotfind.repository.AdminRepository;
import com.mvp.spotfind.repository.ParkingRepository;
import com.mvp.spotfind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public CustomUserDetailsServiceImpl(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String idAsString) throws UsernameNotFoundException {
        Long id = Long.parseLong(idAsString);
        User user = userRepository.findById(id).orElse(null);
        if(user != null) return user;

        Admin admin = adminRepository.findById(id).orElse(null);
        if(admin != null) return admin;

        throw new UsernameNotFoundException("user or admin not found with id :"+ id);
    }

}
