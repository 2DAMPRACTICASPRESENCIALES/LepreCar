package com.svalero.leprecar.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.svalero.leprecar.domain.Car;
import com.svalero.leprecar.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingInDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isAnnullable;
    private long userId;
    private long carId;
}
