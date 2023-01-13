package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Booking;
import com.svalero.leprecar.domain.Car;
import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.exception.NotFoundException;
import com.svalero.leprecar.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Booking> findAll() { return bookingRepository.findAll(); }

    @Override
    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(long id) throws NotFoundException {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Booking()));

        bookingRepository.delete(booking);
    }

    @Override
    public Booking modifyBooking(long id, Booking booking) throws NotFoundException {
        Booking bookingModified = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Booking()));

        modelMapper.map(booking, bookingModified);

        return bookingRepository.save(bookingModified);
    }
}
