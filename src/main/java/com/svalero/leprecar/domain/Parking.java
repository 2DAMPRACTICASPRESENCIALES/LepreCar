package com.svalero.leprecar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "parkings")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String adress;
    @Column
    private boolean isAlwaysOpen;
    @Column
    private float latitude;
    @Column
    private float longitude;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

}
