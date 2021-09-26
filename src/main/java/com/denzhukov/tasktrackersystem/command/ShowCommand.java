package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.UserController;

public class ShowCommand implements Command {
    private final UserController userController;
    private final ProjectController projectController;

    private final static String SHOW_MESSAGE = "List of %s:\n";
    private final static String MISTAKE = "Incorrect request, you can choose users, projects, tasks";

    public ShowCommand(UserController userController, ProjectController projectController) {
        this.userController = userController;
        this.projectController = projectController;
    }

    @Override
    public void execute(String command) {
        String objects = command.split(" ")[1];
        System.out.printf(SHOW_MESSAGE, objects);
        switch (objects) {
            case "users" : userController.showUsers()
                    .forEach(System.out :: println);
            break;
            case "projects" : projectController.showProjects()
                    .forEach(System.out :: println);
            break;
            default: System.out.println(MISTAKE);
            break;
        }
    }
}
