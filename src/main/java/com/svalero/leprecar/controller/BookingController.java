package com.svalero.leprecar.controller;

import com.svalero.leprecar.domain.Booking;
import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.domain.dto.BookingInDTO;
import com.svalero.leprecar.exception.ErrorMessage;
import com.svalero.leprecar.exception.NotFoundException;
import com.svalero.leprecar.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    //private final Logger logger = LoggerFactory.getLogger(ChargingPointController.class);

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getBookings() {
        //logger.debug("end getUsers");
        return new ResponseEntity<>(bookingService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> addBooking(@RequestBody BookingInDTO bookingInDTO) throws NotFoundException {
        //logger.debug("begin addUser");
        Booking newBooking = bookingService.addBooking(bookingInDTO);
        //logger.debug("end addUser");
        return ResponseEntity.status(HttpStatus.OK).body(newBooking);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable long id) throws NotFoundException {
        //logger.debug("begin deleteUser");
        bookingService.deleteBooking(id);
        //logger.debug("end deleteUser");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<Booking> modifyBus(@PathVariable long id, @RequestBody BookingInDTO bookingInDTO) throws NotFoundException{
        //logger.debug("begin modifyUser");
        Booking modifiedBooking = bookingService.modifyBooking(id, bookingInDTO);
        //logger.debug("end modifyUser");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedBooking);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException nfe) {
        //logger.error(nfe.getMessage(), nfe);
        //ErrorMessage errorMessage = new ErrorMessage(404, nfe.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(404, nfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        //logger.error(manve.getMessage(), manve);
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        //logger.error(e.getMessage(), e);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
