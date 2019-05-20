package com.homeworks.homework20.service;

import com.homeworks.homework20.beanpostprocessor.UserBeanPostProcessor;
import com.homeworks.homework20.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerService {
    private static final Logger LOGGER  = LoggerFactory.getLogger(UserBeanPostProcessor.class);

    private final UserService userService;
    private final EmailService emailService;

    @Scheduled(cron = "${cron}")
    public void sendMailtoUsers() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        List<User> usersBirthdays = userService.findAllUsersByBirthDay(month, day);

        usersBirthdays.forEach(user -> {
            try {
                String message = "Happy Birthday " + user.getName() + "!";
                emailService.send(user.getEmail(), "Happy Birthday!", message);
                LOGGER.info("Email have been sent. User " + user.getId() + " Date: " + date);
            } catch (Exception e) {
                LOGGER.error("Email can't be sent. " + e.getMessage());
            }
        });
    }
}
