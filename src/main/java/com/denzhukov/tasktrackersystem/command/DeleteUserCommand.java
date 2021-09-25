package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.UserController;
import com.denzhukov.tasktrackersystem.repository.entity.User;

public class DeleteUserCommand implements Command {
    private final UserController userController;

    private final static String DELETE_MESSAGE_SUCCESS = "User deleted successfully";
    private final static String DELETE_MESSAGE_MISTAKE = "User is not found";


    public DeleteUserCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public void execute(String command) {
        User userDelete = userController.showUsers().stream()
                .filter(user -> user.getFirstName().equalsIgnoreCase(command.split(" ")[1])
                && user.getLastName().equalsIgnoreCase(command.split(" ")[2]))
                .findFirst().orElse(null);
        if(userDelete != null){
            userController.delete(userDelete);
            System.out.println(DELETE_MESSAGE_SUCCESS);
       } else {
            System.out.println(DELETE_MESSAGE_MISTAKE);
        }
    }
}
