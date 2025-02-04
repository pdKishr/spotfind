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

    @PostMapping
    public ResponseEntity<ParkingDto> createParking(@Valid @RequestBody ParkingDto dto){

        ParkingDto dto1 = service.createParking(dto);
        return ResponseEntity.ok(dto1);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Long>> login(@RequestHeader String mobilenumber , @RequestHeader String password){
          Long id = service.login(mobilenumber, password);
          Map<String,Long> map = new HashMap<>();
          map.put("Id",id);
          return ResponseEntity.ok(map);
    }

    @PutMapping("/update")
    public ResponseEntity<ParkingDto> update(@RequestHeader Long id,@Valid @RequestBody ParkingDto dto){
        ParkingDto dto1 = service.updateParking( id , dto);
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
    public ResponseEntity<List<BookingDto>> getBookingByParkingId(@RequestHeader Long id){
       List<BookingDto> list = bookingService.getBookingByParking(id);
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
    public ResponseEntity<List<OfflineBookingDto>>  getOfflineBookingsByparking(@RequestHeader Long id){
        List<OfflineBookingDto> list = offlineBookingService.getTicketByParking(id);
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