package com.mvp.spotfind.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="parking_spots")

public class Parking implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long    id;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(name="mobile_number" , unique = true)
    private String mobileNumber;
    @Column(name="parking_name")
    private String  parkingName;
    private String  owner;
    private String  location;
    private String  address;
    private String  city;
    private String  state;
    @Column(name="is_bike")
    private Boolean isBikeParkingAvailable;
    @Column(name="bike_spots")
    private Integer  noOfBikeSpots=0;
    @Column(name="bike_charge")
    private Integer  bikeCharge;
    @Column(name="is_car")
    private Boolean isCarParkingAvailable;
    @Column(name="car_spots")
    private Integer   noOfCarSpots=0;
    @Column(name="car_charge")
    private Integer   carCharge;
    @Column(name="is_24hours_open")
    private Boolean isAvailableFor24Hours;
    @Column(name="open_time")
    private String  openTime;
    @Column(name="close_time")
    private String  closeTime;
    private Boolean approved;
    @OneToMany(mappedBy = "parking")
    private List<Booking> bookings = new ArrayList<>();
    private Integer availableBikeSpots;
    private Integer availableCarSpots;
    private String role = "PARKING";

    public Parking(String email, String password, String mobileNumber, String parkingName, String owner, String location,String address , String city, String state, Boolean isBikeParkingAvailable, Boolean isCarParkingAvailable, Boolean isAvailableFor24Hours, Integer noOfBikeSpots, Integer noOfCarSpots, Integer bikeCharge, Integer carCharge, String openTime, String closeTime) {
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.parkingName = parkingName;
        this.owner = owner;
        this.location = location;
        this.address = address;
        this.city = city;
        this.state = state;
        this.isBikeParkingAvailable = isBikeParkingAvailable;
        this.isCarParkingAvailable = isCarParkingAvailable;
        this.isAvailableFor24Hours = isAvailableFor24Hours;
        this.noOfBikeSpots = noOfBikeSpots;
        this.noOfCarSpots = noOfCarSpots;
        this.bikeCharge = bikeCharge;
        this.carCharge = carCharge;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.availableBikeSpots = noOfBikeSpots;
        this.availableCarSpots  = noOfCarSpots;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of((new SimpleGrantedAuthority(this.getRole())));
    }

    @Override
    public String getUsername() {
        return String.valueOf(this.getId());
    }
}