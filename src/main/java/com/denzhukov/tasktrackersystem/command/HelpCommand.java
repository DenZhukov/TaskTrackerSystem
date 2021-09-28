package com.denzhukov.tasktrackersystem.command;

public class HelpCommand implements Command{

    public HelpCommand() {
    }

    @Override
    public void execute(String command) {
        System.out.println("Available subjects:" +
                "\n\tuser - user must have first and last name." +
                "\n\t\t He can be assigned to several projects, tasks" +
                "\n\tproject - project must have name(one word)." +
                "\n\t\t It can consist of several tasks and have users" +
                "\n\ttask - task must have name(one word) and executor, belong to one project");
        System.out.println("\nAvailable commands:" +
                "\n\tadd - you can add user, project and task" +
                "\n\t\t add user firstName lastName. Example: add user Ivan Ivanov" +
                "\n\t\t add project projectName. Example: add project Tracker" +
                "\n\t\t add task taskName firstNameExecutor lastNameExecutor projectName. Example: add Prototype Ivan Ivanov Tracker" +
                "\n\tshow - show available users, projects, tasks. Example: show users(projects, tasks)" +
                "\n\tassign - " +
                "\n\treport - " +
                "\n\tdelete - " +
                "\n\texit");
    }
}
