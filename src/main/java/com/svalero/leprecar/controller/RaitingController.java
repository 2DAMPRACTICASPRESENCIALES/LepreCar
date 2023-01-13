package com.svalero.leprecar.controller;

import com.svalero.leprecar.domain.Raiting;
import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.exception.ErrorMessage;
import com.svalero.leprecar.exception.NotFoundException;
import com.svalero.leprecar.service.RaitingService;
import com.svalero.leprecar.service.UserService;
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
public class RaitingController {

    @Autowired
    private RaitingService raitingService;

    //private final Logger logger = LoggerFactory.getLogger(ChargingPointController.class);

    @GetMapping("/raitings")
    public ResponseEntity<List<Raiting>> getRaiting() {
        //logger.debug("end getUsers");
        return new ResponseEntity<>(raitingService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/raitings")
    public ResponseEntity<Raiting> addRaiting(@RequestBody Raiting raiting) {
        //logger.debug("begin addUser");
        Raiting newRaiting = raitingService.addRaiting(raiting);
        //logger.debug("end addUser");
        return ResponseEntity.status(HttpStatus.OK).body(newRaiting);
    }

    @DeleteMapping("/raitings/{id}")
    public ResponseEntity<Void> deleteRaiting(@PathVariable long id) throws NotFoundException {
        //logger.debug("begin deleteUser");
        raitingService.deleteRaiting(id);
        //logger.debug("end deleteUser");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/raitings/{id}")
    public ResponseEntity<Raiting> modifyRaiting(@PathVariable long id, @RequestBody Raiting raiting) throws NotFoundException{
        //logger.debug("begin modifyUser");
        Raiting modifiedRaiting = raitingService.modifyRaiting(id, raiting);
        //logger.debug("end modifyUser");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedRaiting);
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
