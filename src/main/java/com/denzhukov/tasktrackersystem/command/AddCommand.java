package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.pi4j.util.ConsoleColor;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.denzhukov.tasktrackersystem.command.CommandName.ADD;
import static com.denzhukov.tasktrackersystem.console.Messages.FULL_COMMAND;
import static com.denzhukov.tasktrackersystem.console.Messages.NOT_FOUND1;
import static com.denzhukov.tasktrackersystem.console.Subject.*;

public class AddCommand implements Command {
    private final UserController userController;
    private final ProjectController projectController;
    private final TaskController taskController;

    private final static String ADD_USER_MISTAKE = ConsoleColor.RED + "Mistake. Full user's name must consist of first and last name." +
            "\nExample: add user Ivan Ivanov\n" + ConsoleColor.RESET;
    private final static String ADD_USER_PROJECT = ConsoleColor.RED + "Mistake. Project's name must consist of name." +
            "\nExample: add project Tracker\n" + ConsoleColor.RESET;
    private final static String ADD_TASK_MISTAKE = ConsoleColor.RED + "Mistake. Full add task command must consist of name, first and last executor name" +
            ", project name." +
            "\nExample: add task TaskName Ivan Ivanov ProjectName\n" + ConsoleColor.RESET;
    private final static String ADD_SUCCESS = ConsoleColor.GREEN + "%s's added successfully\n" + ConsoleColor.RESET;

    public AddCommand(UserController userController, ProjectController projectController, TaskController taskController) {
        this.userController = userController;
        this.projectController = projectController;
        this.taskController = taskController;
    }

    @Override
    public void execute(String command) {
        if (command.equalsIgnoreCase(ADD.getCommandName())) {
            System.out.println(FULL_COMMAND.getMessage());
            return;
        }
        String[] arrayName = command.split(" ");
        chooseSubject(arrayName);
    }

    //it's my shame..
    private void chooseSubject(String[] commandArray) {
        if (commandArray[1].equalsIgnoreCase(USER.getSubject())) {
            if (commandArray.length == 4)
                addUser(commandArray);
            else System.out.println(ADD_USER_MISTAKE);
        } else if (commandArray[1].equalsIgnoreCase(PROJECT.getSubject())) {
            if (commandArray.length == 3)
                addProject(commandArray);
            else System.out.println(ADD_USER_PROJECT);
        } else if(commandArray[1].equalsIgnoreCase(TASK.getSubject())) {
            if (commandArray.length == 6)
                addTask(commandArray);
            else System.out.println(ADD_TASK_MISTAKE);
        } else System.out.printf(NOT_FOUND1.getMessage(), "Subject");
    }

    private void addUser(String[] commandArray) {
        userController.create(commandArray[2], commandArray[3]);
        System.out.printf(ADD_SUCCESS, USER.getSubject());
    }

    private void addProject(String[] commandArray) {
        if (projectController.create(commandArray[2]))
        System.out.printf(ADD_SUCCESS, PROJECT.getSubject());
    }

    private void addTask(String[] commandArray) {
        Task task = taskController.create(commandArray[2], commandArray[3], commandArray[4], commandArray[5]);
            if (task != null) {
                System.out.printf(ADD_SUCCESS, TASK.getSubject());
                System.out.println(taskController.findTask(commandArray[2], commandArray[5]));

                System.out.println("Is it subtask? Y/N");
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    setSubtask(task, scanner);
                }
            }
        }

    private void setSubtask (Task task, Scanner scanner) {
        List<Task> parents = taskController.showTask().stream()
                .filter(parent -> parent.getProject().getName().equalsIgnoreCase(task.getProject().getName()) && !parent.equals(task))
                .collect(Collectors.toList());
        if (parents.size() > 0) {
            System.out.println("Please, choose the parent task");
            parents.forEach(System.out::println);
            boolean flag = true;
            while (flag) {
                String taskParent = scanner.nextLine();
                if (parents.stream().anyMatch(task1 -> task1.getName().equals(taskParent))) {
                    task.setParentTask(taskParent);
                    taskController.create(task);
                    System.out.println(ConsoleColor.GREEN + "Subtask's created" + ConsoleColor.RESET);
                    flag = false;
                } else if (taskParent.equals("exit")) {
                    flag = false;
                } else {
                    System.out.printf(NOT_FOUND1.getMessage(), TASK.getSubject());
                    System.out.println("Write exit if you change your mind");
                }
            }
        } else System.out.println("It's first task in this project\n");
    }
}
