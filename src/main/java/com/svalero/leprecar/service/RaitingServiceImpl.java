package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Booking;
import com.svalero.leprecar.domain.Car;
import com.svalero.leprecar.domain.Raiting;
import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.domain.dto.BookingInDTO;
import com.svalero.leprecar.domain.dto.RaitingInDTO;
import com.svalero.leprecar.exception.NotFoundException;
import com.svalero.leprecar.repository.CarRepository;
import com.svalero.leprecar.repository.RaitingRepository;
import com.svalero.leprecar.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class RaitingServiceImpl implements RaitingService {

    @Autowired
    private RaitingRepository raitingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Raiting> findAll() {

        return raitingRepository.findAll();
    }

    @Override
    public Raiting addRaiting(RaitingInDTO raitingInDTO) throws NotFoundException {
        Raiting newRaiting = new Raiting();

        User user = userRepository.findById(raitingInDTO.getUserId())
                .orElseThrow(() -> new NotFoundException(new User()));

        Car car = carRepository.findById(raitingInDTO.getCarId())
                .orElseThrow(() -> new NotFoundException(new Car()));

        newRaiting.setComment(raitingInDTO.getComment());
        newRaiting.setRate(raitingInDTO.getRate());
        newRaiting.setDate(raitingInDTO.getDate());
        newRaiting.setUser(user);
        newRaiting.setCar(car);

        return raitingRepository.save(newRaiting);
    }

    @Override
    public void deleteRaiting(long id) throws NotFoundException {
        Raiting raiting = raitingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new User()));

        raitingRepository.delete(raiting);
    }

    @Override
    public Raiting modifyRaiting(long id, RaitingInDTO raitingInDTO) throws NotFoundException {
        Raiting raitingModified = raitingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Raiting()));

        User user = userRepository.findById(raitingInDTO.getUserId())
                .orElseThrow(() -> new NotFoundException(new User()));

        Car car = carRepository.findById(raitingInDTO.getCarId())
                .orElseThrow(() -> new NotFoundException(new Car()));

        modelMapper.map(raitingInDTO, raitingModified);
        raitingModified.setUser(user);
        raitingModified.setCar(car);

        return raitingRepository.save(raitingModified);
    }
}
