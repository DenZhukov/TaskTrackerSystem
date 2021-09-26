package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.User;

import static com.denzhukov.tasktrackersystem.command.CommandName.ADD;

public class AddCommand implements Command {
    private final UserController userController;
    private final ProjectController projectController;

    private final static String ADD_USER_MISTAKE = "Full user's name must consist of first and last name." +
            "\nExample: add user Ivan Ivanov";
    private final static String ADD_MESSAGE = "Please, write full name";
    private final static String ADD_SUCCESS = "%s added successfully\n";

    public AddCommand(UserController userController, ProjectController projectController) {
        this.userController = userController;
        this.projectController = projectController;
    }

    @Override
    public void execute(String command) {
        if (command.equalsIgnoreCase(ADD.getCommandName())) {
            System.out.println(ADD_MESSAGE);
            return;
        }
        String[] arrayName = command.split(" ");
        chooseObject(arrayName);
    }

    private void chooseObject(String[] array){
        if(array[1].equalsIgnoreCase("user"))
            if(array.length == 4)
                addUser(array);
            else System.out.println(ADD_USER_MISTAKE);
        if(array[1].equalsIgnoreCase("project"))
            addProject(array);
    }

    private void addUser(String[] array) {
        User user = new User();
        user.setFirstName(array[2]);
        user.setLastName(array[3]);
        userController.create(user);
        System.out.printf(ADD_SUCCESS, array[1]);
    }

    private void addProject(String[] array) {
        Project project = new Project();
        project.setName(array[2]);
        projectController .create(project);
        System.out.printf(ADD_SUCCESS, array[1]);
    }
}
