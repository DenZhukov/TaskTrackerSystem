package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.UserController;
import com.denzhukov.tasktrackersystem.repository.entity.User;

import static com.denzhukov.tasktrackersystem.command.CommandName.ADD_USER;

public class AddUserCommand implements Command {
    private final UserController userController;

    private final static String ADD_USER_MISTAKE = "Full user's name must consist of first and last name." +
            "\nExample: addUser Ivan Ivanov";
    private final static String ADD_USER_MESSAGE = "Please, write user's first and last name\n"
        + ADD_USER_MISTAKE;
    private final static String ADD_USER_SUCCESS = "User added successfully";

    public AddUserCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public void execute(String command) {
        if (command.equalsIgnoreCase(ADD_USER.getCommandName())) {
            System.out.println(ADD_USER_MESSAGE);
            return;
        }
        String[] arrayFullName = command.split(" ");
        if (arrayFullName.length == 3) {
            User user = new User();
            user.setFirstName(arrayFullName[1]);
            user.setLastName(arrayFullName[2]);
            userController.create(user);
            System.out.println(ADD_USER_SUCCESS);
        } else {
            System.out.println(ADD_USER_MISTAKE);
        }
    }
}
