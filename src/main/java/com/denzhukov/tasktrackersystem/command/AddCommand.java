package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.User;

import java.util.Scanner;

import static com.denzhukov.tasktrackersystem.command.CommandName.ADD;
import static com.denzhukov.tasktrackersystem.console.Subject.*;

public class AddCommand implements Command {
    private final UserController userController;
    private final ProjectController projectController;
    private final TaskController taskController;

    private final static String ADD_USER_MISTAKE = "Full user's name must consist of first and last name." +
            "\nExample: add user Ivan Ivanov";
    private final static String ADD_TASK_MISTAKE = "Full add_task command must consist of name, first and last holder name" +
            ", project name." +
            "\nExample: add task TaskName Ivan Ivanov ProjectName";
    private final static String ADD_MESSAGE = "Please, write full command";
    private final static String ADD_SUCCESS = "%s added successfully\n";

    public AddCommand(UserController userController, ProjectController projectController, TaskController taskController) {
        this.userController = userController;
        this.projectController = projectController;
        this.taskController = taskController;

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

    private void chooseObject(String[] commandArray) {
        if (commandArray[1].equalsIgnoreCase(USER.getSubject())) {
            if (commandArray.length == 4)
                addUser(commandArray);
            else System.out.println(ADD_USER_MISTAKE);
        } else if (commandArray[1].equalsIgnoreCase(PROJECT.getSubject())) {
            addProject(commandArray);
        } else if(commandArray[1].equalsIgnoreCase(TASK.getSubject())) {
            if (commandArray.length > 5)
                addTask(commandArray);
            else System.out.println(ADD_TASK_MISTAKE);
        } else System.out.println("Subject is not found");
    }

    private void addUser(String[] commandArray) {
        User user = new User();
        user.setFirstName(commandArray[2]);
        user.setLastName(commandArray[3]);
        userController.create(user);
        System.out.printf(ADD_SUCCESS, USER.getSubject());
    }

    private void addProject(String[] commandArray) {
        Project project = new Project();
        project.setName(commandArray[2]);
        projectController.create(project);
        System.out.printf(ADD_SUCCESS, PROJECT.getSubject());
    }

    private void addTask(String[] commandArray) {
        taskController.create(commandArray);
        System.out.printf(ADD_SUCCESS, TASK.getSubject());

        System.out.println("Do you want choose executor now? Y/N");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("Y")) {
            AssignCommand assignCommand = new AssignCommand(userController, projectController, taskController);
            boolean flag = true;
            while (flag) {
                System.out.println("Assign executor!");
                String assign = scanner.nextLine();
                if (assign.equalsIgnoreCase("exit"))
                    flag = false;

                //TODO LOOP!
            }
        } else System.out.println("You can do it later.");


    }
}
