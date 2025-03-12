package com.mvp.spotfind.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ParkingDto {
    private Long id;
    @NotNull @Email
    private String email;
    @NotNull @Length(min=10 ,max=10)
    private String mobileNumber;
    @NotNull @Length(min=1)
    private String parkingName;
    @NotNull @Length(min=1)
    private String  location;
    @NotNull @Length(min=1)
    private String address;
    @NotNull @Length(min=1)
    private String  city;
    @NotNull @Length(min=1)
    private String  state;
    @NotNull
    private Boolean  isBikeParkingAvailable;
    @NotNull
    private Integer noOfBikeSpots;
    @NotNull
    private Integer bikeCharge;
    @NotNull
    private Boolean  isCarParkingAvailable;
    @NotNull
    private Integer noOfCarSpots;
    @NotNull
    private Integer carCharge;
    @NotNull
    private Boolean isAvailableFor24Hours;
    @NotNull
    private String  openTime;
    @NotNull
    private String  closeTime;
    private Integer availableBikeSpots;
    private Integer availableCarSpots;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private String pincode;
}