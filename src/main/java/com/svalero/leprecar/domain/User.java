package com.svalero.leprecar.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column
    @NotNull
    @NotBlank(message = "name is required")
    private String name;
    @Column
    @NotNull
    @NotBlank(message = "surname is required")
    private String surnames;
    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;

    /*@OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user_bookings")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user_raitings")
    private List<Raiting> raitings;*/
}
