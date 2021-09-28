package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.denzhukov.tasktrackersystem.command.CommandName.*;

@Component
public class CommandContainer {
    private final Command unknownCommand;
    private final Map<String, Command> commandMap;

    public CommandContainer(UserController userController, ProjectController projectController, TaskController taskController) {
        commandMap = new HashMap<>();
        commandMap.put(SHOW.getCommandName(), new ShowCommand(userController, projectController, taskController));
        commandMap.put(ADD.getCommandName(), new AddCommand(userController, projectController, taskController));
        commandMap.put(DELETE.getCommandName(), new DeleteCommand(userController, projectController, taskController));
        commandMap.put(ASSIGN.getCommandName(), new AssignCommand(userController, projectController, taskController));
        commandMap.put(REPORT.getCommandName(), new ReportCommand(userController, projectController, taskController));
        commandMap.put(HELP.getCommandName(), new HelpCommand());
        unknownCommand = new UnknownCommand();
    }

    public Command findCommand(String command) {
        return commandMap.getOrDefault(command, unknownCommand);
    }
}
