package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Booking;
import com.svalero.leprecar.domain.Car;
import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.domain.dto.BookingInDTO;
import com.svalero.leprecar.exception.NotFoundException;
import com.svalero.leprecar.repository.BookingRepository;
import com.svalero.leprecar.repository.CarRepository;
import com.svalero.leprecar.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Booking> findAll() { return bookingRepository.findAll(); }

    @Override
    public Booking addBooking(BookingInDTO bookingInDTO) throws NotFoundException {
        Booking newBooking = new Booking();

        User user = userRepository.findById(bookingInDTO.getUserId())
                .orElseThrow(() -> new NotFoundException(new User()));

        Car car = carRepository.findById(bookingInDTO.getCarId())
                .orElseThrow(() -> new NotFoundException(new Car()));

        modelMapper.map(bookingInDTO, bookingInDTO);
        newBooking.setUser(user);
        newBooking.setCar(car);

        return bookingRepository.save(newBooking);
    }

    @Override
    public void deleteBooking(long id) throws NotFoundException {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Booking()));

        bookingRepository.delete(booking);
    }

    @Override
    public Booking modifyBooking(long id, BookingInDTO bookingInDTO) throws NotFoundException {
        Booking bookingModified = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Booking()));

        User user = userRepository.findById(bookingInDTO.getUserId())
                .orElseThrow(() -> new NotFoundException(new User()));

        Car car = carRepository.findById(bookingInDTO.getCarId())
                .orElseThrow(() -> new NotFoundException(new Car()));

        modelMapper.map(bookingInDTO, bookingModified);
        bookingModified.setUser(user);
        bookingModified.setCar(car);

        return bookingRepository.save(bookingModified);
    }
}
