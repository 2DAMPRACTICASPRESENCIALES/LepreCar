package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Parking;
import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.exception.NotFoundException;
import com.svalero.leprecar.repository.ParkingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Parking> findAll() {

        return parkingRepository.findAll();
    }

    @Override
    public Parking addParking(Parking parking) {
        return parkingRepository.save(parking);
    }

    @Override
    public void deleteParking(long id) throws NotFoundException {
        Parking parking = parkingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new User()));

        parkingRepository.delete(parking);
    }

    @Override
    public Parking modifyParking(long id, Parking parking) throws NotFoundException {
        Parking parkingModified = parkingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new Parking()));

        modelMapper.map(parking, parkingModified);

        return parkingRepository.save(parkingModified);
    }
}
