package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;

import static com.denzhukov.tasktrackersystem.command.CommandName.SHOW;
import static com.denzhukov.tasktrackersystem.console.Messages.FULL_COMMAND;

public class ShowCommand implements Command {
    private final UserController userController;
    private final ProjectController projectController;
    private final TaskController taskController;

    private final static String SHOW_MESSAGE = "\nList of %s:\n";

    public ShowCommand(UserController userController, ProjectController projectController, TaskController taskController) {
        this.userController = userController;
        this.projectController = projectController;
        this.taskController = taskController;
    }

    @Override
    public void execute(String command) {
        if (command.equalsIgnoreCase(SHOW.getCommandName())) {
            System.out.println(FULL_COMMAND.getMessage());
            return;
        }
        String objects = command.split(" ")[1];
        System.out.printf(SHOW_MESSAGE, objects);
        switch (objects) {
            case "users" : userController.showUsers()
                    .forEach(System.out :: println);
            break;
            case "projects" : projectController.showProjects()
                    .forEach(System.out :: println);
            break;
            case "tasks" : taskController.printTasks(taskController.showTask());
            break;
            default: System.out.println("Incorrect request, you can choose users, projects, tasks");
            break;
        }
    }
}

