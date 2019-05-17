package com.homeworks.homework20.controller;

import com.homeworks.homework20.model.User;
import com.homeworks.homework20.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@ComponentScan(basePackages = "com.homeworks")
public class AppController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @RequestMapping("/add")
    public String addNewUserForm(Map<String, Object> model) {
        model.put("user", new User());
        return "new_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editUserForm(@Valid @PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit_user");
        User user = userService.findUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
