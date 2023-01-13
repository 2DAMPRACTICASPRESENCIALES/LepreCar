package com.svalero.leprecar.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaitingInDTO {

    private String comment;
    private int rate;
    private LocalDate date;
    private long userId;
    private long carId;
}
