package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.exception.NotFoundException;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User addUser(User user);

    void deleteUser(long id) throws NotFoundException;

    User modifyUser(long id, User user) throws NotFoundException;
}
