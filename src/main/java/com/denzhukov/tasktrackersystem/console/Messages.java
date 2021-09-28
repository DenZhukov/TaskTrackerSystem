package com.denzhukov.tasktrackersystem.console;

public enum Messages {
    WELCOME("\t\tWelcome to the TaskTrackerSystem!"),
    LINE("---------------//---------------"),
    FULL_COMMAND("Please, write full command");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
