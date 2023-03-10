package com.svalero.leprecar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
    @Column
    private boolean isAnnullable;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //Relacion Coches Reservas
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;


}
