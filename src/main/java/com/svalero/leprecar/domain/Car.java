package com.svalero.leprecar.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cars")

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private String licensePlate;
    @Column
    private boolean isHybrid;
    @Column
    private int km;
    @Column
    private float hourPrice;

    //Relación cars con booking
    @OneToMany(mappedBy = "car",cascade = CascadeType.REMOVE, orphanRemoval = true) //Para borrar en cascada
    private List<Booking> bookings;
}
