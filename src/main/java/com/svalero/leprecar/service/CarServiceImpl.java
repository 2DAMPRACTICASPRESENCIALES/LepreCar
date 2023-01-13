package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Car;
import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.exception.NotFoundException;
import com.svalero.leprecar.repository.CarRepository;
import com.svalero.leprecar.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Car> findAll() { return carRepository.findAll(); }

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(long id) throws NotFoundException {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Car()));

        carRepository.delete(car);
    }

    @Override
    public Car modifyCar(long id, Car car) throws NotFoundException {
        Car carModify = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Car()));

        modelMapper.map(car, carModify);

        return carRepository.save(carModify);
    }
}
