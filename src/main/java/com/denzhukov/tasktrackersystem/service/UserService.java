package com.denzhukov.tasktrackersystem.service;

import com.denzhukov.tasktrackersystem.repository.entity.User;

import java.util.List;

public interface UserService {
    void create(User user);

    void create(String firstName, String lastName);

    void delete(User user);

    List<User> show();
}
