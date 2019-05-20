package com.homeworks.homework20.service;

import com.homeworks.homework20.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerService { 
    private final UserService userService;
    private final EmailService emailService;

    @Scheduled(cron = "${cron}")
    public void sendMailtoUsers() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        List<User> usersBirthdays = userService.findAllUsersByBirthDay(month, day);
        if (!usersBirthdays.isEmpty()) {
            usersBirthdays.forEach(user -> {
                try {
                    String message = "Happy Birthday " + user.getName() + "!";
                    emailService.send(user.getEmail(), "Happy Birthday!", message);
                    log.info("Email have been sent. User " + user.getId() + " Date: " + date);
                } catch (Exception e) {
                    log.error("Email can't be sent. User " + user.getId() + " Date: " + date);
                    log.error("Email can't be sent. " + e.getMessage());
                }
            });
        }
    }
}
