package com.denzhukov.tasktrackersystem.command;

public enum CommandName {
    SHOW_USERS("showUsers"),
    ADD_USER("addUser"),
    DELETE_USER("deleteUser"),
    HELP("help"),
    EXIT("exit");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
