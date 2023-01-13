package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Car;
import com.svalero.leprecar.exception.NotFoundException;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car addCar(Car car);

    void deleteCar(long id) throws NotFoundException;

    Car modifyCar(long id, Car car) throws NotFoundException;
}
