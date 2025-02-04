package com.mvp.spotfind.service.impls;
import com.mvp.spotfind.Exceptionpack.UserNotFoundException;
import com.mvp.spotfind.dto.ParkingDto;
import com.mvp.spotfind.dto.ParkingTokenDataDto;
import com.mvp.spotfind.entity.Parking;
import com.mvp.spotfind.mapper.ParkingMapper;
import com.mvp.spotfind.repository.ParkingRepository;
import com.mvp.spotfind.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public class ParkingServiceImpl implements ParkingService {

    private ParkingRepository parkingRepository;

    @Autowired
    public ParkingServiceImpl(ParkingRepository parkingRepository){this.parkingRepository = parkingRepository;}

    @Override
    public ParkingDto createParking(ParkingDto dto) {
        Parking p = ParkingMapper.toEntity(dto);
        Parking savedParking = parkingRepository.save(p);
        return ParkingMapper.toDto(savedParking);
    }

    public ParkingTokenDataDto login(String mobileNumber, String password){
          Parking p = parkingRepository
                .findByMobileNumberAndPassword(mobileNumber, password)
                .orElseThrow(() -> new UserNotFoundException("Parking not found with mobile number: " + mobileNumber));

      return ParkingMapper.toParkingTokenDataDto(p);
    }

    @Override
    public ParkingDto updateParking(Long id, ParkingDto dto) {
        Parking p = parkingRepository.findById(id).orElseThrow(()-> new UserNotFoundException("parking not found"));

        for(Field field : ParkingDto.class.getDeclaredFields()){
            field.setAccessible(true); // setting private field accessible

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


}