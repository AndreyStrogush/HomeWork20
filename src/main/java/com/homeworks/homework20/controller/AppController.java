package com.homeworks.homework20.controller;

import com.homeworks.homework20.exeptions.UserNotFaundExeption;
import com.homeworks.homework20.interceptor.UserInterceptor;
import com.homeworks.homework20.model.User;
import com.homeworks.homework20.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@ComponentScan(basePackages = "com.homeworks.homework20.model")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/add")
    public String addNewUserForm(Map<String, Object> model) {
        model.put("user", new User());
        return "new_user";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editUserForm(@Valid @PathVariable(name = "id") Long id) throws UserNotFaundExeption {
        ModelAndView modelAndView = new ModelAndView("edit_user");
        Optional<User> user = userService.findUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
