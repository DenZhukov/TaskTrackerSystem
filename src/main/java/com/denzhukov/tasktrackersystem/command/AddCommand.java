package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import com.pi4j.util.ConsoleColor;

import static com.denzhukov.tasktrackersystem.command.CommandName.ADD;
import static com.denzhukov.tasktrackersystem.console.Messages.FULL_COMMAND;
import static com.denzhukov.tasktrackersystem.console.Subject.*;

public class AddCommand implements Command {
    private final UserController userController;
    private final ProjectController projectController;
    private final TaskController taskController;

    private final static String ADD_USER_MISTAKE = ConsoleColor.RED + "Mistake. Full user's name must consist of first and last name." +
            "\nExample: add user Ivan Ivanov" + ConsoleColor.RESET;
    private final static String ADD_USER_PROJECT = ConsoleColor.RED + "Mistake. Project's name must consist of name." +
            "\nExample: add project Tracker" + ConsoleColor.RESET;
    private final static String ADD_TASK_MISTAKE = ConsoleColor.RED + "Mistake. Full add task command must consist of name, first and last holder name" +
            ", project name." +
            "\nExample: add task TaskName Ivan Ivanov ProjectName" + ConsoleColor.RESET;
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
        } else System.out.println("Subject is not found");
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
        taskController.create(commandArray[2], commandArray[3], commandArray[4], commandArray[5]);
        System.out.printf(ADD_SUCCESS, TASK.getSubject());


        //        System.out.println("Do you want choose executor now? Y/N");
//        Scanner scanner = new Scanner(System.in);
//        String answer = scanner.nextLine();
//        if(answer.equalsIgnoreCase("Y")) {
//            AssignCommand assignCommand = new AssignCommand(userController, projectController, taskController);
//            boolean flag = true;
//            while (flag) {
//                System.out.println("Assign executor!");
//                String assign = scanner.nextLine();
//                if (assign.equalsIgnoreCase("exit"))
//                    flag = false;
//
//                //TODO LOOP!
//            }
//        } else System.out.println("You can do it later.");
    }
}
