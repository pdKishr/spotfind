package com.mvp.spotfind.service.impls;

import com.mvp.spotfind.Exceptionpack.BookingFullException;
import com.mvp.spotfind.Exceptionpack.UserNotFoundException;
import com.mvp.spotfind.dto.BookingDto;
import com.mvp.spotfind.entity.Booking;
import com.mvp.spotfind.entity.Parking;
import com.mvp.spotfind.entity.User;
import com.mvp.spotfind.mapper.BookingMapper;
import com.mvp.spotfind.repository.BookingRepository;
import com.mvp.spotfind.repository.ParkingRepository;
import com.mvp.spotfind.repository.UserRepository;
import com.mvp.spotfind.service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ParkingRepository parkingRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, ParkingRepository parkingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.parkingRepository = parkingRepository;
        this.userRepository    = userRepository;
    }

    @Transactional
    @Override
    public BookingDto bookBikeTicket(Long userId, Long parkingId, String vehicleNumber) {
        User user       = userRepository.findById(userId).orElse(null);
        Parking parking = parkingRepository.findById(parkingId).orElseThrow(()-> new UserNotFoundException("parking not found with Id "+parkingId));

        if(parking.getAvailableBikeSpots()<=0){
            throw new BookingFullException("booking full");
        }

        parking.setAvailableBikeSpots(parking.getAvailableBikeSpots()-1);
        parkingRepository.save(parking);

        Booking booking = new Booking();
        if(user != null) booking.setUser(user);
        booking.setParking(parking);
        booking.setVehicleType("bike");
        booking.setVehicleNumber(vehicleNumber);

        booking = bookingRepository.save(booking);

        return BookingMapper.toDto(booking);
    }

    @Transactional
    @Override
    public BookingDto bookCarTicket(Long userId, Long parkingId, String vehicleNumber) {

        User user       = userRepository.findById(userId).orElse(null);
        Parking parking = parkingRepository.findById(parkingId).orElseThrow(()-> new UserNotFoundException("parking not found with Id "+parkingId));

        if(parking.getAvailableCarSpots()<=0){
            throw new BookingFullException("booking full");
        }

        parking.setAvailableCarSpots(parking.getAvailableCarSpots()-1);
        parkingRepository.save(parking);

        Booking booking = new Booking();
        if(user != null) booking.setUser(user);
        booking.setParking(parking);
        booking.setVehicleType("car");
        booking.setVehicleNumber(vehicleNumber);

        booking = bookingRepository.save(booking);

        return BookingMapper.toDto(booking);
    }

    public List<BookingDto> getBookingByUser(Long userId){
        List<Booking> bookings = new ArrayList<>();
        bookings =  bookingRepository.findByUserId(userId);

        List<BookingDto> bookingDtos = new ArrayList<>();
        for(Booking b : bookings){
            bookingDtos.add(BookingMapper.toDto(b));
        }

        return bookingDtos;
    }

    public List<BookingDto> getBookingByParking(Long parkingId){
        List<Booking> bookings = new ArrayList<>();
        bookings =  bookingRepository.findByParkingId(parkingId);

        List<BookingDto> bookingDtos = new ArrayList<>();
        for(Booking b : bookings){
            bookingDtos.add(BookingMapper.toDto(b));
        }

        return bookingDtos;
    }

    @Transactional
    @Override
    public void checkout(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).
                orElseThrow(()-> new UserNotFoundException("Booking not found with id "+bookingId));

        booking.setIsCheckOut(true);

        booking.setCheckedOutAt(LocalDateTime.now());

        if(Objects.equals(booking.getVehicleType(), "bike")){
            Integer spots = booking.getParking().getAvailableBikeSpots();
            booking.getParking().setAvailableBikeSpots(spots+1);
        }
        else{
            Integer spots = booking.getParking().getAvailableCarSpots();
            booking.getParking().setAvailableCarSpots(spots+1);
        }

        bookingRepository.save(booking);
    }
}