package com.homeworks.homework20.service;

import com.homeworks.homework20.exeptions.UserNotFaundExeption;
import com.homeworks.homework20.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(User user);

    void updateUser(User user);

    boolean deleteUser(User user);

    void deleteUser(Long id);

    Optional<User> findUserById(Long id) throws UserNotFaundExeption;

    List<User> findAllUsers();

    List<User> findAllUsersByBirthDay(int month, int day);

    User findUserByEmail(String email);
}
