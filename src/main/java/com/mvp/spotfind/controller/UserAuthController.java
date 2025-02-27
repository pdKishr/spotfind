package com.mvp.spotfind.controller;

import com.mvp.spotfind.dto.UserDto;
import com.mvp.spotfind.dto.UserTokenDataDto;
import com.mvp.spotfind.JwtUtilPackage.JwtUtil;
import com.mvp.spotfind.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/user")

public class UserAuthController {

    private final UserService  userService;
    private final JwtUtil      jwtUtil;

    @Autowired
    public UserAuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto dto){
        UserDto dto1 = userService.createUser(dto);
        return ResponseEntity.ok(dto1);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestHeader String mobilenumber, @RequestHeader String password){

        UserTokenDataDto dto = userService.login(mobilenumber, password);
        String token = jwtUtil.generateToken(dto.getId(), dto.getRole());
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("id",Long.toString(dto.getId()));

        return ResponseEntity.ok(map);
    }

}
