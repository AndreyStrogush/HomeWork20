package com.homeworks.homework20.service;

import com.homeworks.homework20.exeptions.UserNotFaundExeption;
import com.homeworks.homework20.model.User;
import com.homeworks.homework20.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean deleteUser(User user) {
        userRepository.delete(user);
        return true;
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserById(Long id) throws UserNotFaundExeption {
        return Optional.of(userRepository.findById(id).orElseThrow(() -> new UserNotFaundExeption(id)));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllUsersByBirthDay(int month, int day) {
        return userRepository.findByMatchMonthAndMatchDay(month,day);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
