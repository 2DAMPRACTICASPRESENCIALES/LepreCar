package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Raiting;
import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.domain.dto.RaitingInDTO;
import com.svalero.leprecar.exception.NotFoundException;

import java.util.List;

public interface RaitingService {
    List<Raiting> findAll();

    Raiting addRaiting(RaitingInDTO raiting) throws NotFoundException;

    void deleteRaiting(long id) throws NotFoundException;

    Raiting modifyRaiting(long id, RaitingInDTO raitingInDTO) throws NotFoundException;
}
