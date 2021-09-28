package com.denzhukov.tasktrackersystem.service;

import com.denzhukov.tasktrackersystem.repository.entity.User;
import com.denzhukov.tasktrackersystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUser(String firstName, String lastName) {
        return show().stream()
                .filter(user1 -> user1.getFirstName().equalsIgnoreCase(firstName) && user1.getLastName().equalsIgnoreCase(lastName))
                .findFirst().orElse(null);
    }

    @Override
    public void create(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> show() { return userRepository.findAll(); }
}
