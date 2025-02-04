package com.mvp.spotfind.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookingDto {
    private Long   id;
    private String userName;
    private String parkingName;
    private String parkingAddress;
    private String city;
    private String state;
    private String vehicleType;
    private String vehicleNumber;
    private LocalDateTime BookedAt;
    private Boolean isCheckOut;
    private LocalDateTime CheckedAtOut;
}
