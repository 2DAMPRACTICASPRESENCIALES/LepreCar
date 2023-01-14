package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Parking;
import com.svalero.leprecar.exception.NotFoundException;

import java.util.List;

public interface ParkingService {
    List<Parking> findAll();

    Parking addParking(Parking parking);

    void deleteParking(long id) throws NotFoundException;

    Parking modifyParking(long id, Parking parking) throws NotFoundException;
}
