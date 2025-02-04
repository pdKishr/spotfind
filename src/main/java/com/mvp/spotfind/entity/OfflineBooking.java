package com.mvp.spotfind.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class OfflineBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="mobile_number")
    private String mobileNumber;

    @ManyToOne
    @JoinColumn(name="parkingId" , nullable=false)
    private Parking parking;

    @Column(name="vehicle_type" , nullable = false)
    private String vehicleType;

    @Column(name="vehicle_number" , nullable = false)
    private String VehicleNumber;

    @CreationTimestamp
    @Column(name="booked_at")
    private LocalDateTime BookedAt;

    @Column(name="is_check_out")
    private Boolean isCheckOut = false;

    @Column(name="checkedout_at")
    private LocalDateTime checkedOutAt;
}