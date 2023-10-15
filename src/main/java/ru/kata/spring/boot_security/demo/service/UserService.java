package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserById(Long id);

    void updateUser(User updateUser, Long id);

    void saveUser(User user);

    void deleteUserById(long id);


}


