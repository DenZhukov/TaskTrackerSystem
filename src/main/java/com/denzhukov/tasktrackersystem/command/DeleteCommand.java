package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.denzhukov.tasktrackersystem.repository.entity.User;

import static com.denzhukov.tasktrackersystem.console.Subject.*;

public class DeleteCommand implements Command {
    private final UserController userController;
    private final ProjectController projectController;
    private final TaskController taskController;

    private final static String DELETE_MESSAGE_SUCCESS = "%s deleted successfully\n";
    private final static String DELETE_MESSAGE_MISTAKE = "%s is not found\n";


    public DeleteCommand(UserController userController, ProjectController projectController, TaskController taskController) {
        this.userController = userController;
        this.projectController = projectController;
        this.taskController = taskController;
    }

    @Override
    public void execute(String command) {
        String[] arrayCommand = command.split(" ");

        chooseDeleteObject(arrayCommand);
    }

    private void chooseDeleteObject(String[] arrayCommand){
        if (arrayCommand[1].equalsIgnoreCase(USER.getSubject()))
            deleteUser(arrayCommand);
        if (arrayCommand[1].equalsIgnoreCase(PROJECT.getSubject()))
            deleteProject(arrayCommand);
        if (arrayCommand[1].equalsIgnoreCase(TASK.getSubject()))
            deleteTask(arrayCommand);
    }

    private void deleteUser(String[] arrayCommand) {
        User userDelete = userController.showUsers().stream()
                .filter(user -> user.getFirstName().equalsIgnoreCase(arrayCommand[2])
                        && user.getLastName().equalsIgnoreCase(arrayCommand[3]))
                .findFirst().orElse(null);
        if(userDelete != null){
            userController.delete(userDelete);
            System.out.printf(DELETE_MESSAGE_SUCCESS, USER.getSubject());
        } else {
            System.out.printf(DELETE_MESSAGE_MISTAKE, USER.getSubject());
        }
    }

    private void deleteProject(String[] arrayCommand) {
        Project projectDelete = projectController.showProjects().stream()
                .filter(project -> project.getName().equalsIgnoreCase(arrayCommand[2]))
                .findFirst().orElse(null);
        if(projectDelete != null){
             projectController.delete(projectDelete);
            System.out.printf(DELETE_MESSAGE_SUCCESS, PROJECT.getSubject());
        } else {
            System.out.printf(DELETE_MESSAGE_MISTAKE, PROJECT.getSubject());
        }
    }

    private void deleteTask(String[] arrayCommand) {
        Task taskDelete = taskController.showTask().stream()
                .filter(task -> task.getName().equalsIgnoreCase(arrayCommand[2]))
                .findFirst().orElse(null);
        if(taskDelete != null){
            taskController.delete(taskDelete);
            System.out.printf(DELETE_MESSAGE_SUCCESS, TASK.getSubject());
        } else {
            System.out.printf(DELETE_MESSAGE_MISTAKE, TASK.getSubject());
        }
    }


}
