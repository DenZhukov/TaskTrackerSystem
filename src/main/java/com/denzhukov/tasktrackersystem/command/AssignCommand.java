package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.denzhukov.tasktrackersystem.repository.entity.User;

import static com.denzhukov.tasktrackersystem.command.CommandName.ASSIGN;
import static com.denzhukov.tasktrackersystem.console.Subject.PROJECT;
import static com.denzhukov.tasktrackersystem.console.Subject.TASK;

public class AssignCommand implements Command{
    private final TaskController taskController;
    private final UserController userController;
    private final ProjectController projectController;

    private final static String ASSIGN_MESSAGE = "Please, write full command";
    private final static String ASSIGN_TASK_MISTAKE = "Full assign user on the task command must consist of name task, first and last executor name" +
             "\nExample: assign task TaskName Ivan Ivanov";


    public AssignCommand(UserController userController, ProjectController projectController, TaskController taskController) {
        this.taskController = taskController;
        this.userController = userController;
        this.projectController = projectController;
    }

    @Override
    public void execute(String command) {
        if (command.equalsIgnoreCase(ASSIGN.getCommandName())) {
            System.out.println(ASSIGN_MESSAGE);
            return;
        }
        String[] arrayTask = command.split(" ");
        chooseObject(arrayTask);
    }

    private void chooseObject(String[] commandArray) {
       if (commandArray[1].equalsIgnoreCase(PROJECT.getSubject())) {
            assignProject(commandArray);
        } else if (commandArray[1].equalsIgnoreCase(TASK.getSubject())) {
           if (commandArray.length > 3)
                assignTask(commandArray);
           else System.out.println(ASSIGN_TASK_MISTAKE);
        } else System.out.println("Subject is not found");
    }
//TODO REFACTOR
    private void assignTask(String[] arrayTask) {
        Task task = taskController.showTask().stream()
                .filter(task1 -> task1.getName().equalsIgnoreCase(arrayTask[2]))
                .findFirst().orElse(null);
        User user = userController.showUsers().stream()
                .filter(user1 -> user1.getFirstName().equalsIgnoreCase(arrayTask[3]) && user1.getLastName().equalsIgnoreCase(arrayTask[4]))
                .findFirst().orElse(null);
        if (task != null && user != null) {
            task.setUserExecutor(user);
            taskController.create(task);
            System.out.println("User was assigned on the task ");
        } else System.out.println("Task or User don't exist, please check list of users or tasks " +
                "(command \"show users(or tasks)\").");
    }

    private void assignProject(String[] arrayProject) {
        User user = userController.showUsers().stream()
                .filter(user1 -> user1.getFirstName().equalsIgnoreCase(arrayProject[3]) && user1.getLastName().equalsIgnoreCase(arrayProject[4]))
                .findFirst().orElse(null);
        Project project = projectController.showProjects().stream()
                .filter(project1 -> project1.getName().equalsIgnoreCase(arrayProject[2]))
                .findFirst().orElse(null);
        if (user != null && project != null) {
            project.addUser(user);
            projectController.create(project);
            System.out.println("User was assigned on the project ");
        } else System.out.println("Project or User don't exist, please check list of users or tasks " +
                "(command \"show users(or project)\").");
    }
}
