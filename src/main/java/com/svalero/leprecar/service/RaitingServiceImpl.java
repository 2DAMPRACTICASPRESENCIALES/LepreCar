package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Raiting;
import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.exception.NotFoundException;
import com.svalero.leprecar.repository.RaitingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaitingServiceImpl implements RaitingService {

    @Autowired
    private RaitingRepository raitingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Raiting> findAll() {

        return raitingRepository.findAll();
    }

    @Override
    public Raiting addRaiting(Raiting raiting) {
        return raitingRepository.save(raiting);
    }

    @Override
    public void deleteRaiting(long id) throws NotFoundException {
        Raiting raiting = raitingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new User()));

        raitingRepository.delete(raiting);
    }

    @Override
    public Raiting modifyRaiting(long id, Raiting raiting) throws NotFoundException {
        Raiting raitingModified = raitingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new User()));

        modelMapper.map(raiting, raitingModified);

        return raitingRepository.save(raitingModified);
    }
}
