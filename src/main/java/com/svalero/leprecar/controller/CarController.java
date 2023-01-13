package com.svalero.leprecar.controller;

import com.svalero.leprecar.domain.Car;
import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.domain.dto.CarInDTO;
import com.svalero.leprecar.exception.ErrorMessage;
import com.svalero.leprecar.exception.NotFoundException;
import com.svalero.leprecar.service.CarService;
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
public class CarController {

    @Autowired
    private CarService carService;

    //private final Logger logger = LoggerFactory.getLogger(ChargingPointController.class);

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCar(@RequestParam(name = "brand", defaultValue = "", required = false) String brand) {
        //logger.debug("end getUsers");
        if(brand.equals(""))
            return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);

        return new ResponseEntity<>(carService.findCarByBrand(brand), HttpStatus.OK);
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> addCar(@RequestBody CarInDTO carInDTO) throws NotFoundException {
        //logger.debug("begin addUser");
        Car newCar = carService.addCar(carInDTO);
        //logger.debug("end addUser");
        return ResponseEntity.status(HttpStatus.OK).body(newCar);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable long id) throws NotFoundException {
        //logger.debug("begin deleteUser");
        carService.deleteCar(id);
        //logger.debug("end deleteUser");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> modifyCar(@PathVariable long id, @RequestBody CarInDTO carInDTO) throws NotFoundException{
        //logger.debug("begin modifyUser");
        Car modifiedCar = carService.modifyCar(id, carInDTO);
        //logger.debug("end modifyUser");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedCar);
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
