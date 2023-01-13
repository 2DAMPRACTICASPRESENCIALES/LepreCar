package com.svalero.leprecar.repository;

import com.svalero.leprecar.domain.Booking;
import com.svalero.leprecar.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car,Long> {

   List<Car> findAll();
}
