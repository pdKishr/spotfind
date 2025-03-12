package com.mvp.spotfind.controller;

import com.mvp.spotfind.Exceptionpack.UserNotFoundException;
import com.mvp.spotfind.OpenCageMapsServices.GeocodingService;
import com.mvp.spotfind.dto.BookingDto;
import com.mvp.spotfind.dto.ParkingDto;
import com.mvp.spotfind.dto.UserDto;
import com.mvp.spotfind.dto.UserProfileUpdateDto;
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
    private final GeocodingService geocodingService;

    @Autowired
    public UserController(UserService userService, BookingService bookingService, ParkingService parkingService, GeocodingService geocodingService) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.parkingService = parkingService;
        this.geocodingService = geocodingService;
    }

    @GetMapping("/get_user_details")
    public ResponseEntity<UserDto> getUser(@RequestParam  Long id){
        UserDto dto = userService.getUser(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> update(@RequestHeader Long id, @RequestBody @Valid UserProfileUpdateDto dto){
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

    @GetMapping("/booking/get-booking") public ResponseEntity<List<BookingDto>> showTickets(@RequestParam Long id){
             List<BookingDto> list = bookingService.getBookingByUser(id);
             return ResponseEntity.ok(list);
    }

    @GetMapping("/getparking")
    public ResponseEntity<ParkingDto> getParking(@RequestParam Long id){
        ParkingDto dto = parkingService.getParkingById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getAllParking")
    public ResponseEntity<List<ParkingDto>> getAllParking(){
        List<ParkingDto> dto = parkingService.getAllApprovedParking();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getParking/around-specified-location")
    public ResponseEntity<List<ParkingDto>> getParking(@RequestParam String location , @RequestParam String vehicleType, @RequestParam String city ){

        String address = location + ", "+ city+", India";
        Double[] coordinates = geocodingService.getCoordinates(address);
        if(coordinates.length==0){
             coordinates = geocodingService.getCoordinates(city+", India");
             if(coordinates.length == 0) throw new UserNotFoundException("unable to detect specified location");
        }

        List<ParkingDto> dto = parkingService.getAllParkingByNearByLocation(coordinates[0],coordinates[1],10000.0);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getParking/around-current-location")
    public ResponseEntity<List<ParkingDto>> getParkingByNearestLocation(@RequestParam Double latitude , @RequestParam Double longitude , @RequestParam String vehicleType ){
        List<ParkingDto> dto = parkingService.getAllParkingByNearByLocation(latitude,longitude,5000.0);
        return ResponseEntity.ok(dto);
    }

}