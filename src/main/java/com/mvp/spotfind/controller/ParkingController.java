package com.mvp.spotfind.controller;
import com.mvp.spotfind.dto.BookingDto;
import com.mvp.spotfind.dto.OfflineBookingDto;
import com.mvp.spotfind.dto.ParkingDto;
import com.mvp.spotfind.service.BookingService;
import com.mvp.spotfind.service.OfflineBookingService;
import com.mvp.spotfind.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/parking")
public class ParkingController {
    private final ParkingService service ;
    private final BookingService bookingService;
    private final OfflineBookingService offlineBookingService;

    @Autowired
    public ParkingController(ParkingService service , BookingService bookingService, OfflineBookingService offlineBookingService){
        this.service = service;
        this.bookingService = bookingService;
        this.offlineBookingService = offlineBookingService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createParking(@Valid @RequestBody ParkingDto dto, @RequestParam Long id){

         service.createParking(dto,id);
         return ResponseEntity.ok("Parking Created");


    }

    @GetMapping("/getAllParkingById")
    public ResponseEntity<List<ParkingDto>> getAllParkingBYId(@RequestParam Long id){
       List<ParkingDto> list = service.getAllParkingById(id);
       return ResponseEntity.ok(list);
    }

    @PutMapping("/update")
    public ResponseEntity<ParkingDto> update(@Valid @RequestBody ParkingDto dto){
        System.out.println("reacheing here 123");
        ParkingDto dto1 = service.updateParking( dto.getId(), dto);
        return ResponseEntity.ok(dto1);
    }

    @PostMapping("/booking/checkout/online")
    public ResponseEntity<Map<String,String>> onlineBookingcheckout(@RequestHeader Long bookingid){
        bookingService.checkout(bookingid);
        Map<String,String>  map= new HashMap<>();
        map.put("msg","checkedOut successfully");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/booking/online")
    public ResponseEntity<List<BookingDto>> getBookingByParkingId(@RequestParam Long parkingId){
       List<BookingDto> list = bookingService.getBookingByParking(parkingId);
       return ResponseEntity.ok(list);
    }

    @PostMapping("/booking/offline-book-bike")
    public ResponseEntity<OfflineBookingDto> generateBiketicket(@RequestHeader Long id,@RequestHeader String mobilenumber,@RequestHeader String vehiclenumber){
        OfflineBookingDto dto = offlineBookingService.bookBikeTicketOffline(id,mobilenumber,vehiclenumber);
        return  ResponseEntity.ok(dto);
    }

    @PostMapping("/booking/offline-book-car")
    public ResponseEntity<OfflineBookingDto> generateCarticket(@RequestHeader Long id,@RequestHeader String mobilenumber,@RequestHeader String vehiclenumber){
        OfflineBookingDto dto = offlineBookingService.bookCarTicketOffline(id,mobilenumber,vehiclenumber);
        return  ResponseEntity.ok(dto);
    }

    @GetMapping("/booking/offline")
    public ResponseEntity<List<OfflineBookingDto>>  getOfflineBookingsByparking(@RequestParam Long parkingId){
        List<OfflineBookingDto> list = offlineBookingService.getTicketByParking(parkingId);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/booking/checkout/offline")
    public ResponseEntity<Map<String,String>> OfflineBookingCheckout(@RequestHeader Long bookingid){
        offlineBookingService.checkout(bookingid);
        Map<String,String>  map= new HashMap<>();
        map.put("msg","checkedOut successfully");
        return ResponseEntity.ok(map);
    }





}