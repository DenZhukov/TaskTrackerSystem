package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.User;

public class DeleteCommand implements Command {
    private final UserController userController;
    private final ProjectController projectController;

    private final static String DELETE_MESSAGE_SUCCESS = "%s deleted successfully\n";
    private final static String DELETE_MESSAGE_MISTAKE = "%s is not found\n";


    public DeleteCommand(UserController userController, ProjectController projectController) {
        this.userController = userController;
        this.projectController = projectController;
    }

    @Override
    public void execute(String command) {
        String[] arrayCommand = command.split(" ");

        chooseDeleteObject(arrayCommand);
    }

    private void chooseDeleteObject(String[] arrayCommand){
        if(arrayCommand[1].equalsIgnoreCase("user"))
            deleteUser(arrayCommand);
        if(arrayCommand[1].equalsIgnoreCase("project"))
            deleteProject(arrayCommand);
    }

    private void deleteUser(String[] arrayCommand) {
        User userDelete = userController.showUsers().stream()
                .filter(user -> user.getFirstName().equalsIgnoreCase(arrayCommand[2])
                        && user.getLastName().equalsIgnoreCase(arrayCommand[3]))
                .findFirst().orElse(null);
        if(userDelete != null){
            userController.delete(userDelete);
            System.out.printf(DELETE_MESSAGE_SUCCESS, "User");
        } else {
            System.out.printf(DELETE_MESSAGE_MISTAKE, "User");
        }
    }

    private void deleteProject(String[] arrayCommand) {
        Project projectDelete = projectController.showProjects().stream()
                .filter(project -> project.getName().equalsIgnoreCase(arrayCommand[2]))
                .findFirst().orElse(null);
        if(projectDelete != null){
             projectController.delete(projectDelete);
            System.out.printf(DELETE_MESSAGE_SUCCESS, "Project");
        } else {
            System.out.printf(DELETE_MESSAGE_MISTAKE, "Project");
        }
    }


}
