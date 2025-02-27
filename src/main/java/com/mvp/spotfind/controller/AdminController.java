package com.mvp.spotfind.controller;

import com.mvp.spotfind.dto.AdminParkingViewDto;
import com.mvp.spotfind.service.AdminService;
import com.mvp.spotfind.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final ParkingService parkingService;

    public AdminController(AdminService adminService, ParkingService parkingService) {
        this.adminService = adminService;
        this.parkingService = parkingService;
    }

    @PostMapping("/approve_parking")
    public  ResponseEntity<Map<String,String>> approveParking(@RequestHeader Long id){
        adminService.approveParking(id);
        Map<String,String> map = new HashMap<>();
        map.put("msg","approved parking");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/approveAll")
    public ResponseEntity<String> approveAll(){
        adminService.approveAllParking();
        return ResponseEntity.ok("success");
    }


    @GetMapping("/getAllParking")
    public ResponseEntity<List<AdminParkingViewDto>> getAllParking(){
        List<AdminParkingViewDto> list = parkingService.getAllParking();
        return  ResponseEntity.ok(list);
    }
}