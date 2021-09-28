package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.denzhukov.tasktrackersystem.repository.entity.User;
import com.pi4j.util.ConsoleColor;

import static com.denzhukov.tasktrackersystem.command.CommandName.ASSIGN;
import static com.denzhukov.tasktrackersystem.console.Messages.*;
import static com.denzhukov.tasktrackersystem.console.Subject.PROJECT;
import static com.denzhukov.tasktrackersystem.console.Subject.TASK;

public class AssignCommand implements Command{
    private final TaskController taskController;
    private final UserController userController;
    private final ProjectController projectController;

    private final static String ASSIGN_TASK_MISTAKE = ConsoleColor.RED + "Assign user on the task command must consist of name task, project name, first and last executor name" +
             "\nExample: assign task ProjectName TaskName Ivan Ivanov" + ConsoleColor.RESET;
    private final static String ASSIGN_PROJECT_MISTAKE = ConsoleColor.RED + "Assign user on the project command must consist of name project, first and last executor name" +
            "\nExample: assign project ProjectName Ivan Ivanov" + ConsoleColor.RESET;


    public AssignCommand(UserController userController, ProjectController projectController, TaskController taskController) {
        this.taskController = taskController;
        this.userController = userController;
        this.projectController = projectController;
    }

    @Override
    public void execute(String command) {
        if (command.equalsIgnoreCase(ASSIGN.getCommandName())) {
            System.out.println(FULL_COMMAND.getMessage());
            return;
        }
        String[] arrayTask = command.split(" ");
        chooseSubject(arrayTask);
    }

    private void chooseSubject(String[] commandArray) {
       if (commandArray[1].equalsIgnoreCase(PROJECT.getSubject())) {
           if (commandArray.length == 5) {
               assignProject(commandArray);
           } else System.out.println(ASSIGN_PROJECT_MISTAKE);
       } else if (commandArray[1].equalsIgnoreCase(TASK.getSubject())) {
           if (commandArray.length == 6) {
               assignTask(commandArray);
           } else System.out.println(ASSIGN_TASK_MISTAKE);
       } else System.out.printf(NOT_FOUND1.getMessage(), commandArray[1]);
    }

    private void assignTask(String[] arrayTask) {
        Task task = taskController.findTask(arrayTask[2], arrayTask[3]);
        User user = userController.findUser(arrayTask[4], arrayTask[5]);
        if (task != null && user != null) {
            task.setUserExecutor(user);
            taskController.create(task);
            System.out.printf(ASSIGN_USER.getMessage(), TASK.getSubject());
        } else System.out.printf(ASSIGN_SUB.getMessage(), TASK.getSubject(), TASK.getSubject(), TASK.getSubject());
    }

    private void assignProject(String[] arrayProject) {
        User user = userController.findUser(arrayProject[3], arrayProject[4]);
        Project project = projectController.findProject(arrayProject[2]);
        if (user != null && project != null) {
            project.addUser(user);
            projectController.create(project);
            System.out.printf(ASSIGN_USER.getMessage(), PROJECT.getSubject());
        } else System.out.printf(ASSIGN_SUB.getMessage(), PROJECT.getSubject(), PROJECT.getSubject(), PROJECT.getSubject());
    }
}
