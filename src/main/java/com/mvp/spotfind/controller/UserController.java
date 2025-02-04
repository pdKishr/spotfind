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

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService    userService;
    private final BookingService bookingService;
    private final ParkingService parkingService;

    @Autowired
    public UserController(UserService userService, BookingService bookingService, ParkingService parkingService) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.parkingService = parkingService;
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