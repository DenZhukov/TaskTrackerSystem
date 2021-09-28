package com.denzhukov.tasktrackersystem.command;

import static com.denzhukov.tasktrackersystem.console.Messages.LINE;

public class HelpCommand implements Command{

    @Override
    public void execute(String command) {
        System.out.println("Available subjects:" +
                "\n\tuser - user must have first and last name." +
                "\n\t\t He can be assigned to several projects, tasks" +
                "\n\tproject - project must have name(one word)." +
                "\n\t\t It can consist of several tasks and have users" +
                "\n\ttask - task must have name(one word) and executor, belong to one project");
        System.out.print(LINE.getMessage() + LINE.getMessage());
        System.out.println("\nAvailable commands:" +
                "\n\tadd - you can add user, project and task" +
                "\n\t\t add user firstName lastName. Example: add user Ivan Ivanov" +
                "\n\t\t add project projectName. Example: add project TrackerSystem" +
                "\n\t\t add task taskName firstNameExecutor lastNameExecutor projectName. Example: add Prototype Ivan Ivanov TrackerSystem\n" +
                        LINE.getMessage() + LINE.getMessage() +
                "\n\tshow - show available users, projects, tasks. Example: show users(projects, tasks)\n" +
                        LINE.getMessage() + LINE.getMessage() +
                "\n\tassign - assign user on the projects or tashelpks." +
                "\n\t\t assign project projectName firstNameExecutor lastNameExecutor. Example: assign project TrackerSystem Ivan Ivanov" +
                "\n\t\t assign task taskName projectName firstNameExecutor lastNameExecutor. Example: assign task MVP TrackerSystem Ivan Ivanov\n" +
                        LINE.getMessage() + LINE.getMessage() +
                "\n\treport - generate the report of all tasks created for specified Projects by specified User" +
                "\n\t\t Example: report TrackerSystem Ivan Ivanov\n" +
                        LINE.getMessage() + LINE.getMessage() +
                "\n\tdelete - delete subject (user, project, task). Example: delete user Ivan Ivanov" +
                "\n\t\t Attention! If you delete project or user you will lose all assigned tasks\n" +
                        LINE.getMessage() + LINE.getMessage() +
                "\n\texit - exiting the application");
    }
}
