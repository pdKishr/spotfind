package com.mvp.spotfind.controller;

import com.mvp.spotfind.dto.BookingDto;
import com.mvp.spotfind.dto.ParkingDto;
import com.mvp.spotfind.dto.UserDto;
import com.mvp.spotfind.service.BookingService;
import com.mvp.spotfind.service.ParkingService;
import com.mvp.spotfind.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService    userService;
    private BookingService bookingService;
    private ParkingService parkingService;

    @Autowired
    public UserController(UserService userService, BookingService bookingService, ParkingService parkingService) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.parkingService = parkingService;
    }

    @PostMapping
    public ResponseEntity<UserDto>  create(@Valid @RequestBody UserDto dto){
        UserDto dto1 = userService.createUser(dto);
        return ResponseEntity.ok(dto1);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Long>> login(@RequestHeader  String mobilenumber,@RequestHeader String password){
         Long id = userService.login(mobilenumber, password);
         Map<String,Long> map = new HashMap<>();
         map.put("id",id);
         return ResponseEntity.ok(map);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> update(@RequestHeader Long id,@RequestBody @Valid UserDto dto){
         UserDto dto1 = userService.updateUser(id,dto);
         return ResponseEntity.ok(dto1);
    }

    @PostMapping("/booking/book-bike-spot")
    public ResponseEntity<BookingDto> bookBikeTicket(@RequestHeader Long id,@RequestHeader Long parkingid, @RequestHeader String vehiclenumber){
        BookingDto dto = bookingService.bookBikeTicket(id,parkingid,vehiclenumber);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/booking/book-car-spot")
    public ResponseEntity<BookingDto> bookCarTicket(@RequestHeader Long id,@RequestHeader Long parkingid, @RequestHeader String vehiclenumber){
        BookingDto dto = bookingService.bookCarTicket(id,parkingid,vehiclenumber);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/booking/get-booking")
    public ResponseEntity<List<BookingDto>> showTickets(@RequestHeader Long id){
             List<BookingDto> list = bookingService.getBookingByUser(id);
             return ResponseEntity.ok(list);
    }

    @GetMapping("/getparking")
    public ResponseEntity<ParkingDto> getParking(@RequestHeader Long id){
        ParkingDto dto = parkingService.getParkingById(id);
        return ResponseEntity.ok(dto);
    }

}