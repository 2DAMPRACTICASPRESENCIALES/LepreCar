package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Car;
import com.svalero.leprecar.domain.Parking;
import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.domain.dto.CarInDTO;
import com.svalero.leprecar.exception.NotFoundException;
import com.svalero.leprecar.repository.CarRepository;
import com.svalero.leprecar.repository.ParkingRepository;
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
    private ParkingRepository parkingRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Car> findAll() { return carRepository.findAll(); }

    @Override
    public Car addCar(CarInDTO carInDTO) throws NotFoundException {
        Car car = new Car();

        Parking parking = parkingRepository.findById(carInDTO.getParkingId())
                .orElseThrow(() -> new NotFoundException(new Parking()));

        modelMapper.map(carInDTO, car);
        car.setParking(parking);

        return carRepository.save(car);
    }

    @Override
    public void deleteCar(long id) throws NotFoundException {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Car()));

        carRepository.delete(car);
    }

    @Override
    public Car modifyCar(long id, CarInDTO carInDTO) throws NotFoundException {
        Car carModify = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Car()));

        Parking parking = parkingRepository.findById(carInDTO.getParkingId())
                .orElseThrow(() -> new NotFoundException(new Parking()));

        modelMapper.map(carInDTO, carModify);
        carModify.setParking(parking);

        return carRepository.save(carModify);
    }
}
