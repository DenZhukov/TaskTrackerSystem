package com.denzhukov.tasktrackersystem.controller;

import com.denzhukov.tasktrackersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.denzhukov.tasktrackersystem.repository.entity.User;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> showUsers() {
        return userService.show();
    }

    public void create(User user) {
        userService.create(user);
    }

    public void create(String firstName, String lastName) {
        userService.create(firstName, lastName);
    }

    public User findUser(String firstName, String lastName) {
        return userService.findUser(firstName, lastName);
    }

    public void delete(User user) {
        userService.delete(user);
    }
}
