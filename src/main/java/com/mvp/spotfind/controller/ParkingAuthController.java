package com.mvp.spotfind.controller;

import com.mvp.spotfind.dto.ParkingDto;
import com.mvp.spotfind.dto.ParkingTokenDataDto;
import com.mvp.spotfind.security.JwtUtil;
import com.mvp.spotfind.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth/parking")
public class ParkingAuthController {
    private final ParkingService service ;
    private final JwtUtil jwtUtil;

    public ParkingAuthController(ParkingService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<ParkingDto> createParking(@Valid @RequestBody ParkingDto dto){

        ParkingDto dto1 = service.createParking(dto);
        return ResponseEntity.ok(dto1);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestHeader String mobilenumber , @RequestHeader String password){
        ParkingTokenDataDto dto = service.login(mobilenumber, password);
        String token = jwtUtil.generateToken(dto.getId() , dto.getRole());
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        return ResponseEntity.ok(map);
    }
}
