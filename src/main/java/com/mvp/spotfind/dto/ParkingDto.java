package com.mvp.spotfind.dto;

import com.mvp.spotfind.entity.User;
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
    @NotNull
    private String parkingName;
    @NotNull
    private String  location;
    @NotNull
    private String address;
    @NotNull
    private String  city;
    @NotNull
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

}