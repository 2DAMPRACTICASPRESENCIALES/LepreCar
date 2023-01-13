package com.svalero.leprecar.repository;

import com.svalero.leprecar.domain.Raiting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RaitingRepository extends CrudRepository<Raiting, Long> {
    List<Raiting> findAll();
}
