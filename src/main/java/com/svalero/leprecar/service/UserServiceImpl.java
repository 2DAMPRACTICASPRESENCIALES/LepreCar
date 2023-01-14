package com.svalero.leprecar.service;

import com.svalero.leprecar.domain.User;
import com.svalero.leprecar.exception.NotFoundException;
import com.svalero.leprecar.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new User()));

        userRepository.delete(user);
    }

    @Override
    public User modifyUser(long id, User user) throws NotFoundException {
        User userModified = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new User()));

        userModified.setName(user.getName());
        userModified.setSurnames(user.getSurnames());
        userModified.setBirthDate(user.getBirthDate());

        return userRepository.save(userModified);
    }
}
