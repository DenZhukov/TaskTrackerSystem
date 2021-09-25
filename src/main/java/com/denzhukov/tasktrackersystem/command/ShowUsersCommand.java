package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.UserController;

public class ShowUsersCommand implements Command {
    private final UserController userController;

    private final static String SHOW_USERS_MESSAGE = "List of Users:";

    public ShowUsersCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public void execute(String command) {
        System.out.println(SHOW_USERS_MESSAGE);
        userController.showUsers().stream()
                .forEach(System.out :: println);
    }
}
