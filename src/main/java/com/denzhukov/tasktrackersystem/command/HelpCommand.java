package com.denzhukov.tasktrackersystem.command;

import static com.denzhukov.tasktrackersystem.console.Messages.LINE;

public class HelpCommand implements Command{

    @Override
    public void execute(String command) {
        System.out.println(LINE.getMessage() + LINE.getMessage() +
                "\n\tAvailable subjects:" +
                "\n\tuser - user must have first and last name." +
                "\n\t\t He can be assigned to several projects, tasks" +
                "\n\tproject - project must have name(one word)." +
                "\n\t\t It can consist of several tasks and have users" +
                "\n\ttask - task must have name(one word) and executor, belong to one project" +
                "\n\t\tEvery task can have (and be) subtask and have deadline;" +
                "\n\t\tTask would closed(deleted) if remaining time for a task " +
                "\n\t\twas 0 day and all of its subtasks are closed");
        System.out.print(LINE.getMessage() + LINE.getMessage());
        System.out.println("\n\tAvailable commands:" +
                "\n\tadd - you can add user, project and task" +
                "\n\t\t add user firstName lastName. Example: add user Ivan Ivanov" +
                "\n\t\t add project projectName. Example: add project TrackerSystem" +
                "\n\t\t add task taskName firstNameExecutor lastNameExecutor " +
                "\n\t\tprojectName. Example: add Prototype Ivan Ivanov TrackerSystem\n" +
                        LINE.getMessage() + LINE.getMessage() +
                "\n\tshow - show available users, projects, tasks. " +
                "\n\t\tExample: show users(projects, tasks)\n" +
                        LINE.getMessage() + LINE.getMessage() +
                "\n\tassign - assign user on the projects or tasks." +
                "\n\t\t assign project projectName firstNameExecutor lastNameExecutor." +
                "\n\t\tExample: assign project TrackerSystem Ivan Ivanov" +
                "\n\t\t assign task taskName projectName firstNameExecutor " +
                "\n\t\tlastNameExecutor. " +
                "\n\t\tExample: assign task MVP TrackerSystem Ivan Ivanov\n" +
                        LINE.getMessage() + LINE.getMessage() +
                "\n\treport - generate the report of all tasks created for specified " +
                "\n\t\tprojects by specified User" +
                "\n\t\t Example: report TrackerSystem Ivan Ivanov\n" +
                        LINE.getMessage() + LINE.getMessage() +
                "\n\tdeadline - set deadline for tasks. Strong date format: dd.MM.yyyy" +
                "\n\t\tExample: deadline taskName projectName 31.12.2021\n" +
                "\n\tremain - show remaining days for task and its subtask" +
                "\n\t\tExample: remain taskName projectName" +
                "\n\t\tYou could see all tasks with deadline just write \"remain\"\n" +
                        LINE.getMessage() + LINE.getMessage() +
                "\n\tdelete - delete subject (user, project, task). " +
                "\n\t\tExample: delete user Ivan Ivanov" +
                "\n\tAttention! If you delete project or user you will lose all assigned tasks\n" +
                        LINE.getMessage() + LINE.getMessage() +
                "\n\texit - exiting the application");
    }
}
