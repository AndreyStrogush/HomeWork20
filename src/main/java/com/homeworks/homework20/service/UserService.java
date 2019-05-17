package com.homeworks.homework20.service;

import com.homeworks.homework20.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User findUserById(Long id);

    List<User> findAllUsers();

    List<User> findAllUsersByBirthDay(int month, int day);

    User findUserByEmail(String email);
}
