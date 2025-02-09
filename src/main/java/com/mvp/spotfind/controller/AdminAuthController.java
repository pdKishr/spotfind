package com.mvp.spotfind.controller;

import com.mvp.spotfind.dto.AdminDataTokenDto;
import com.mvp.spotfind.JwtUtilPackage.JwtUtil;
import com.mvp.spotfind.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/admin")
public class AdminAuthController {
    private final AdminService service;
    private final JwtUtil jwtUtil;

    public AdminAuthController(AdminService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestHeader String mobilenumber, @RequestHeader String password){

        AdminDataTokenDto dto = service.login(mobilenumber, password);
        String token = jwtUtil.generateToken(dto.getId(), dto.getRole());
        Map<String,String> map = new HashMap<>();
        map.put("token",token);

        return ResponseEntity.ok(map);
    }

}
