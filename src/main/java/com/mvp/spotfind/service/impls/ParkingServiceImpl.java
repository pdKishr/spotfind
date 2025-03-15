package com.mvp.spotfind.service.impls;
import com.mvp.spotfind.Exceptionpack.UserNotFoundException;
import com.mvp.spotfind.OpenCageMapsServices.GeocodingService;
import com.mvp.spotfind.dto.AdminParkingViewDto;
import com.mvp.spotfind.dto.ParkingDto;
import com.mvp.spotfind.entity.Parking;
import com.mvp.spotfind.entity.User;
import com.mvp.spotfind.mapper.ParkingMapper;
import com.mvp.spotfind.repository.ParkingRepository;
import com.mvp.spotfind.repository.UserRepository;
import com.mvp.spotfind.service.ParkingService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTWriter;
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

    private final GeometryFactory geometryFactory = new GeometryFactory();
    private final ParkingRepository parkingRepository;
    private final UserRepository userRepository;
    private final GeocodingService geocodingService;

    @Autowired
    public ParkingServiceImpl(ParkingRepository parkingRepository, UserRepository userRepository, GeocodingService geocodingService){
        this.parkingRepository = parkingRepository;
        this.userRepository = userRepository;
        this.geocodingService = geocodingService;
    }

    @Override
    public void createParking(ParkingDto parkingDTO, Long owner_id) {

        User user_owner =  userRepository.findById(owner_id).orElseThrow(()-> new UserNotFoundException("User not found"));
        Parking parking = ParkingMapper.toEntity(parkingDTO,user_owner);
        String address = parking.getAddress() + ", " + parking.getCity() + ", " +
                parking.getState() +", "+ parking.getPincode() +", India";

        Double[] coordinates = geocodingService.getCoordinates(address);
        if(coordinates.length == 0) throw new UserNotFoundException("coordinates not found on the address");
        parking.setLatitude(coordinates[0]);
        parking.setLongitude(coordinates[1]);

        parking = parkingRepository.save(parking);


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
    public List<ParkingDto> getAllParkingByNearByLocation(Double latitude, Double longitude, Double radius) {

     /*  Point searchPoint = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        searchPoint.setSRID(4326);


        WKTWriter wktWriter = new WKTWriter();
        String searchPointWKT = wktWriter.write(searchPoint);
        System.out.println(searchPointWKT+ ": this is search point pd");
        List<Parking> parkingLots = parkingRepository.findNearByParkingLots(searchPointWKT,radius);
        if(parkingLots.isEmpty())   return List.of();

        return parkingLots.stream().map(ParkingMapper:: toDto).collect(Collectors.toList());*/
        return List.of();

    }

}