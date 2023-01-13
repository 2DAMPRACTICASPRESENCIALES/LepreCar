package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.Raiting;
import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.exception.NotFoundException;

import java.util.List;

public interface RaitingService {
    List<Raiting> findAll();

    Raiting addRaiting(Raiting raiting);

    void deleteRaiting(long id) throws NotFoundException;

    Raiting modifyRaiting(long id, Raiting raiting) throws NotFoundException;
}
