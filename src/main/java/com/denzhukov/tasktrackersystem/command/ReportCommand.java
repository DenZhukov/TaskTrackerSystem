package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.User;

import java.util.stream.Collectors;

import static com.denzhukov.tasktrackersystem.command.CommandName.REPORT;

public class ReportCommand implements Command {
    private final UserController userController;
    private final ProjectController projectController;
    private final TaskController taskController;

    private final String REPORT_MESSAGE = "Please, write full command\n" +
            "Example: report projectName executorName";

    public ReportCommand(UserController userController, ProjectController projectController, TaskController taskController) {
        this.userController = userController;
        this.projectController = projectController;
        this.taskController = taskController;
    }

    @Override
    public void execute(String command) {
        if (command.equalsIgnoreCase(REPORT.getCommandName())) {
            System.out.println(REPORT_MESSAGE);
            return;
        }

        String[] commandArray = command.split(" ");

        Project project = projectController.showProjects().stream()
                .filter(project1 -> project1.getName().equalsIgnoreCase(commandArray[1]))
                .findAny().orElse(null);

        User user = userController.showUsers().stream()
                .filter(user1 -> user1.getFirstName().equalsIgnoreCase(commandArray[2]) && user1.getLastName().equalsIgnoreCase(commandArray[3]))
                .findFirst().orElse(null);
        if (project != null && user != null) {
          taskController.showTask().stream()
                  .filter(task -> task.getProject().getId().equals(project.getId()) && task.getUserHolder().getId().equals(user.getId()))
                  .collect(Collectors.toList())
                  .forEach(System.out :: println);
        } else System.out.println("User or project are not found");
    }
}
