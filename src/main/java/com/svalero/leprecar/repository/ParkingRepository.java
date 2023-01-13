package com.svalero.leprecar.repository;

import com.svalero.leprecar.domain.Car;
import com.svalero.leprecar.domain.Parking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository  extends CrudRepository<Parking,Long> {
    List<Parking> findAll();
}
