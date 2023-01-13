package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Booking;
import com.svalero.leprecar.domain.dto.BookingInDTO;
import com.svalero.leprecar.exception.NotFoundException;

import java.util.List;

public interface BookingService {
    List<Booking> findAll();

    Booking addBooking(BookingInDTO bookingInDTO) throws NotFoundException;

    void deleteBooking(long id) throws NotFoundException;

    Booking modifyBooking(long id, BookingInDTO bookingInDTO) throws NotFoundException;

}
