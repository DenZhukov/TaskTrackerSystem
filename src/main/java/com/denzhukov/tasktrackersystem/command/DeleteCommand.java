package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.denzhukov.tasktrackersystem.repository.entity.User;
import com.pi4j.util.ConsoleColor;

import static com.denzhukov.tasktrackersystem.console.Messages.FULL_COMMAND;
import static com.denzhukov.tasktrackersystem.console.Messages.NOT_FOUND1;
import static com.denzhukov.tasktrackersystem.console.Subject.*;

public class DeleteCommand implements Command {
    private final UserController userController;
    private final ProjectController projectController;
    private final TaskController taskController;

    private final static String DELETE_MESSAGE_SUCCESS = ConsoleColor.GREEN + "%s deleted successfully\n" + ConsoleColor.RESET;

    public DeleteCommand(UserController userController, ProjectController projectController, TaskController taskController) {
        this.userController = userController;
        this.projectController = projectController;
        this.taskController = taskController;
    }

    @Override
    public void execute(String command) {
        String[] arrayCommand = command.split(" ");
        chooseDeleteSubject(arrayCommand);
    }

    private void chooseDeleteSubject(String[] arrayCommand){
        if (arrayCommand[1].equalsIgnoreCase(USER.getSubject()))
            if (arrayCommand.length == 4)
                deleteUser(arrayCommand);
            else System.out.println(FULL_COMMAND.getMessage());
        else if (arrayCommand[1].equalsIgnoreCase(PROJECT.getSubject()))
            if (arrayCommand.length == 3)
                deleteProject(arrayCommand);
            else System.out.println(FULL_COMMAND.getMessage());
        else if (arrayCommand[1].equalsIgnoreCase(TASK.getSubject()))
            if (arrayCommand.length == 4)
                deleteTask(arrayCommand);
            else System.out.println(FULL_COMMAND.getMessage());
        else System.out.printf(NOT_FOUND1.getMessage(), "Subject");
    }

    private void deleteUser(String[] arrayCommand) {
        User userDelete = userController.findUser(arrayCommand[2], arrayCommand[3]);
        if(userDelete != null) {
            userController.delete(userDelete);
            System.out.printf(DELETE_MESSAGE_SUCCESS, USER.getSubject());
        } else {
            System.out.printf(NOT_FOUND1.getMessage(), USER.getSubject());
        }
    }

    private void deleteProject(String[] arrayCommand) {
        Project projectDelete = projectController.findProject(arrayCommand[2]);
        if(projectDelete != null) {
            projectController.delete(projectDelete);
            System.out.printf(DELETE_MESSAGE_SUCCESS, PROJECT.getSubject());
        } else {
            System.out.printf(NOT_FOUND1.getMessage(), PROJECT.getSubject());
        }
    }

    private void deleteTask(String[] arrayCommand) {
        Task taskDelete = taskController.findTask(arrayCommand[2], arrayCommand[3]);
        if(taskDelete != null){
            taskController.delete(taskDelete);
            System.out.printf(DELETE_MESSAGE_SUCCESS, TASK.getSubject());
        } else {
            System.out.printf(NOT_FOUND1.getMessage(), TASK.getSubject());
        }
    }
}
