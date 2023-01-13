package com.svalero.leprecar.domain.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarInDTO {

    private String brand;
    private String model;
    private String licensePlate;
    private boolean isHybrid;
    private int km;
    private float hourPrice;
    private long parkingId;
}
