package com.mvp.spotfind.service.impls;

import com.mvp.spotfind.Exceptionpack.BookingFullException;
import com.mvp.spotfind.Exceptionpack.UserNotFoundException;
import com.mvp.spotfind.dto.OfflineBookingDto;
import com.mvp.spotfind.entity.OfflineBooking;
import com.mvp.spotfind.entity.Parking;
import com.mvp.spotfind.mapper.OfflineBookingMapper;
import com.mvp.spotfind.repository.OfflineBookingRepository;
import com.mvp.spotfind.repository.ParkingRepository;
import com.mvp.spotfind.service.OfflineBookingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OfflineBookingServiceImpl implements OfflineBookingService {
    private final OfflineBookingRepository offlineBookingRepository;
    private final ParkingRepository parkingRepository;

    public OfflineBookingServiceImpl(OfflineBookingRepository offlineBookingRepository,   ParkingRepository parkingRepository) {
        this.offlineBookingRepository = offlineBookingRepository;
        this.parkingRepository = parkingRepository;
    }

    @Override
    public OfflineBookingDto bookBikeTicketOffline(Long parkingId, String mobileNumber, String vehicleNumber) {
        Parking parking = parkingRepository.findById(parkingId).orElseThrow(()-> new UserNotFoundException("parking not found with Id "+parkingId));

        if(parking.getAvailableBikeSpots()<=0){
            throw new BookingFullException("booking full");
        }

        parking.setAvailableBikeSpots(parking.getAvailableBikeSpots()-1);
        parkingRepository.save(parking);

        OfflineBooking booking = new OfflineBooking();
        booking.setMobileNumber(mobileNumber);
        booking.setParking(parking);
        booking.setVehicleType("bike");
        booking.setVehicleNumber(vehicleNumber);

        booking = offlineBookingRepository.save(booking);

        return OfflineBookingMapper.toDto(booking);
    }

    @Override
    public OfflineBookingDto bookCarTicketOffline(Long parkingId, String mobileNumber, String vehicleNumber) {
        Parking parking = parkingRepository.findById(parkingId).orElseThrow(()-> new UserNotFoundException("parking not found with Id "+parkingId));

        if(parking.getAvailableCarSpots()<=0){
            throw new BookingFullException("booking full");
        }

        parking.setAvailableCarSpots(parking.getAvailableCarSpots()-1);
        parkingRepository.save(parking);

        OfflineBooking booking = new OfflineBooking();
        booking.setMobileNumber(mobileNumber);
        booking.setParking(parking);
        booking.setVehicleType("bike");
        booking.setVehicleNumber(vehicleNumber);

        booking = offlineBookingRepository.save(booking);

        return OfflineBookingMapper.toDto(booking);
    }

    @Override
    public List<OfflineBookingDto> getTicketByParking(Long parkingId) {
        List<OfflineBooking> bookings = new ArrayList<>();
        bookings =  offlineBookingRepository.findByParkingId(parkingId);

        List<OfflineBookingDto> bookingDtos = new ArrayList<>();
        for(OfflineBooking b : bookings){
            bookingDtos.add(OfflineBookingMapper.toDto(b));
        }

        return bookingDtos;
    }

    @Transactional
    @Override
    public void checkout(Long bookingId) {
        OfflineBooking booking = offlineBookingRepository.findById(bookingId).
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

        offlineBookingRepository.save(booking);
    }

}