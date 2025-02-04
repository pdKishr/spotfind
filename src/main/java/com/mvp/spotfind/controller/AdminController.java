package com.mvp.spotfind.controller;

import com.mvp.spotfind.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/approve_parking")
    public  ResponseEntity<Map<String,String>> approveParking(@RequestHeader Long id){
        service.approveParking(id);
        Map<String,String> map = new HashMap<>();
        map.put("msg","approved parking");
        return ResponseEntity.ok(map);
    }
}