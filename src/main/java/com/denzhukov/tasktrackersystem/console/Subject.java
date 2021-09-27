package com.denzhukov.tasktrackersystem.console;

public enum Subject {
    USER("user"),
    PROJECT("project"),
    TASK("task");

    private final String subject;

    Subject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
