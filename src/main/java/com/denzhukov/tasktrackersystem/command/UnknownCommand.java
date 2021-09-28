package com.denzhukov.tasktrackersystem.command;

public class UnknownCommand implements Command {

    public UnknownCommand() {
    }

    @Override
    public void execute(String command) {
        System.out.println("Unknown command. Please, write \"help\" " +
                "to find out the available commands");
    }
}
