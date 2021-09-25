package com.denzhukov.tasktrackersystem.command;

public class UnknownCommand implements Command {

    private final static String UNKNOWN_COMMAND_MESSAGE = "Unknown command. Please, write \"help\" " +
            "to find out the available commands";

    public UnknownCommand() {
    }

    @Override
    public void execute(String command) {
        System.out.println(UNKNOWN_COMMAND_MESSAGE);
    }
}
