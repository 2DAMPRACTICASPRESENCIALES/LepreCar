package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Car;
import com.svalero.leprecar.domain.dto.CarInDTO;
import com.svalero.leprecar.exception.NotFoundException;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car addCar(CarInDTO carInDTO) throws NotFoundException;

    void deleteCar(long id) throws NotFoundException;

    Car modifyCar(long id, CarInDTO carInDTO) throws NotFoundException;

    List<Car> findCarByBrand(String brand);
}
