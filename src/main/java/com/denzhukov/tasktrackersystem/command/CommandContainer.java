package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.UserController;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.denzhukov.tasktrackersystem.command.CommandName.*;

@Component
public class CommandContainer {
    private final Command unknownCommand;
    private final Map<String, Command> commandMap;

    public CommandContainer(UserController userController) {
        commandMap = new HashMap<String, Command>();
        commandMap.put(SHOW_USERS.getCommandName(), new ShowUsersCommand(userController));
        commandMap.put(ADD_USER.getCommandName(), new AddUserCommand(userController));
        commandMap.put(DELETE_USER.getCommandName(), new DeleteUserCommand(userController));

        unknownCommand = new UnknownCommand();
    }

    public Command findCommand(String command) {
        return commandMap.getOrDefault(command, unknownCommand);
    }
}
