package com.mvp.spotfind.service.impls;
import com.mvp.spotfind.Exceptionpack.UserNotFoundException;
import com.mvp.spotfind.dto.AdminParkingViewDto;
import com.mvp.spotfind.dto.ParkingDto;
import com.mvp.spotfind.entity.Parking;
import com.mvp.spotfind.entity.User;
import com.mvp.spotfind.mapper.ParkingMapper;
import com.mvp.spotfind.repository.ParkingRepository;
import com.mvp.spotfind.repository.UserRepository;
import com.mvp.spotfind.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements ParkingService {

    private ParkingRepository parkingRepository;
    private UserRepository userRepository;

    @Autowired
    public ParkingServiceImpl(ParkingRepository parkingRepository, UserRepository userRepository){
        this.parkingRepository = parkingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ParkingDto createParking(ParkingDto parkingDTO, Long owner_id) {
        User owner = userRepository.findById(owner_id).orElseThrow(()-> new UserNotFoundException("UserNotFound"));

        Parking parking = ParkingMapper.toEntity(parkingDTO, owner);
        Parking savedParking = parkingRepository.save(parking);

        return ParkingMapper.toDto(savedParking);
    }

    @Override
    public ParkingDto updateParking(Long id, ParkingDto dto) {

        Parking p = parkingRepository.findById(id).orElseThrow(()-> new UserNotFoundException("parking not found"));

       for(Field field : ParkingDto.class.getDeclaredFields()){
            field.setAccessible(true); // setting private field accessible

           if (field.getName().equals("id")) continue;

            try{
                Object value = field.get(dto);

                if(value != null){
                    String setterName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    Method setter = Parking.class.getMethod(setterName, field.getType());
                    setter.invoke(p , value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }



        Parking updatedParking = parkingRepository.save(p);
        return ParkingMapper.toDto(updatedParking);
    }

    @Override
    public ParkingDto getParkingById(Long id) {
        Parking p = parkingRepository.findById(id).orElseThrow(()-> new UserNotFoundException("parking not found with id "+id));
        return ParkingMapper.toDto(p);
    }

    @Override
    public List<AdminParkingViewDto> getAllParking(){
        List<Parking> parkings = parkingRepository.findAll();
        List<AdminParkingViewDto> dtos = new ArrayList<>();
        for(Parking p : parkings){
            dtos.add(ParkingMapper.toAdminParkingViewDto(p));
        }
        return dtos;
    }

    @Override
    public List<ParkingDto> getAllApprovedParking() {
        List<Parking> parkings = parkingRepository.findAllByApproved(true);
        if(parkings.isEmpty()) return List.of();

        List<ParkingDto> dto = new ArrayList<>();
        for(Parking p : parkings){
            dto.add(ParkingMapper.toDto(p));
        }

        return dto;
    }

    @Override
    public List<ParkingDto> getAllParkingById(Long ownerId) {
        List<Parking> parkings = parkingRepository.findByOwnerId(ownerId);
        if(parkings.isEmpty()) return List.of();

        List<ParkingDto> dto = new ArrayList<>();
        for(Parking p : parkings){
            dto.add(ParkingMapper.toDto(p));
        }

        return dto;
    }

    @Override
    public List<ParkingDto> getParkingByFilter(String location, String vehicleType, String city) {

        List<Parking> parkings;

        if(vehicleType.equals("car"))
             parkings   = parkingRepository.findByLocationAndCityAndApprovedAndIsCarParkingAvailable(location,city,true, true);
        else{
            parkings = parkingRepository.findByLocationAndCityAndApprovedAndIsBikeParkingAvailable(location,city,true,true);
        }

        if(parkings.isEmpty()) return List.of();

        List<ParkingDto> dtos = new ArrayList<>();
        for(Parking p : parkings) dtos.add(ParkingMapper.toDto(p));

        return dtos;
    }

    @Override
    public List<ParkingDto> getparking(String location, String city, String vehicleType) {
        location = location.toLowerCase();
        city = city.toLowerCase();
        vehicleType = vehicleType.toLowerCase();

        List<Parking> parkings = parkingRepository.searchParking(location,city,vehicleType);
        if(parkings.isEmpty()) return List.of();

        return parkings.stream().map(ParkingMapper::toDto).collect(Collectors.toList());
    }

}