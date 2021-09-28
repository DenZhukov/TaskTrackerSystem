package com.denzhukov.tasktrackersystem.console;

import com.pi4j.util.ConsoleColor;

public enum Messages {
    WELCOME("\t\tWelcome to the TaskTrackerSystem!"),
    NOT_FOUND(ConsoleColor.RED + "%s or %s are not found" + ConsoleColor.RESET),
    NOT_FOUND1(ConsoleColor.RED + "%s is not found\n" + ConsoleColor.RESET),
    ASSIGN_SUB(ConsoleColor.RED + "%s or User don't exist, please check list of users or %s\n" +
            "                (command \"show users(or %s)\"\n)." + ConsoleColor.RESET),
    ASSIGN_USER(ConsoleColor.GREEN + "User was assigned on the %s\n" + ConsoleColor.RESET),
    LINE("---------------//---------------"),
    FULL_COMMAND(ConsoleColor.YELLOW + "Please, write full command" + ConsoleColor.RESET);

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
