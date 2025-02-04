package com.mvp.spotfind.repository;

import com.mvp.spotfind.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String> {
}
