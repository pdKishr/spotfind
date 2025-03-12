package com.mvp.spotfind.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="parking_spots")

public class Parking  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long    id;
    private String email;
    @Column(name="mobile_number")
    private String mobileNumber;
    @Column(name="parking_name")
    private String  parkingName;
    @ManyToOne
    @JoinColumn(name="owner_id" , referencedColumnName = "id")
    private User    owner;
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
    private Double latitude;
    private Double longitude;
    private String pincode;

    public Parking(@NotNull @Email String email, @NotNull @Length(min=10 ,max=10) String mobileNumber, @NotNull String parkingName, User owner, @NotNull String location, @NotNull String address, @NotNull String city, @NotNull String state, @NotNull Boolean isBikeParkingAvailable, @NotNull Boolean isCarParkingAvailable, @NotNull Boolean isAvailableFor24Hours, @NotNull Integer noOfBikeSpots, @NotNull Integer noOfCarSpots, @NotNull Integer bikeCharge, @NotNull Integer carCharge, @NotNull String openTime, @NotNull String closeTime,@NotNull Double latitude ,@NotNull Double longitude , @NotNull String pincode) {

        this.email = email;
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
        this.availableBikeSpots = this.noOfBikeSpots;
        this.availableCarSpots  = this.noOfCarSpots;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pincode  = pincode;
    }


}