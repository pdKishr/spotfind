package com.mvp.spotfind.repository;

import com.mvp.spotfind.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByMobileNumberAndPassword(String mobileNumber, String password);
}
