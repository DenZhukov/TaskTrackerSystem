package com.denzhukov.tasktrackersystem.console;

import com.denzhukov.tasktrackersystem.command.CommandContainer;
import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.pi4j.util.ConsoleColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.denzhukov.tasktrackersystem.command.CommandName.EXIT;
import static com.denzhukov.tasktrackersystem.console.Messages.WELCOME;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CommandContainer commandContainer;
    private final UserController userController;
    private final ProjectController projectController;
    private final TaskController taskController;

    @Autowired
    public ConsoleRunner(CommandContainer commandContainer, UserController userController,
                         ProjectController projectController, TaskController taskController) {
        this.commandContainer = commandContainer;
        this.userController = userController;
        this.projectController = projectController;
        this.taskController = taskController;
    }

    @Override
    public void run(String... args) throws Exception {
        InputStart input = new InputStart(userController, projectController, taskController);
        input.start();

        System.out.println(WELCOME.getMessage());
        System.out.println("You can create users, projects and tasks. Assign users to projects and tasks.\nAnd much more!" +
               " Write " + ConsoleColor.GREEN +"help" + ConsoleColor.RESET +" if you want to know more!");


        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String command = "";
            while (!command.equalsIgnoreCase(EXIT.getCommandName())) {
                command = reader.readLine();
                commandContainer.findCommand(command.split(" ")[0]).execute(command);
            }
        }catch(IOException e){
                e.printStackTrace();
        }
    }
}
