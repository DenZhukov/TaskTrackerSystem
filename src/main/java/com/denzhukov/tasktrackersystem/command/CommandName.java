package com.denzhukov.tasktrackersystem.command;

public enum CommandName {
    SHOW("show"),
    ADD("add"),
    DELETE("delete"),
    ASSIGN("assign"),
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
