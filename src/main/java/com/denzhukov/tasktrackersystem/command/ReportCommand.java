package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.User;
import com.pi4j.util.ConsoleColor;

import java.util.stream.Collectors;

import static com.denzhukov.tasktrackersystem.command.CommandName.REPORT;
import static com.denzhukov.tasktrackersystem.console.Messages.NOT_FOUND;
import static com.denzhukov.tasktrackersystem.console.Subject.*;

public class ReportCommand implements Command {
    private final UserController userController;
    private final ProjectController projectController;
    private final TaskController taskController;

    public ReportCommand(UserController userController, ProjectController projectController, TaskController taskController) {
        this.userController = userController;
        this.projectController = projectController;
        this.taskController = taskController;
    }

    @Override
    public void execute(String command) {
        if (command.equalsIgnoreCase(REPORT.getCommandName())) {
            System.out.println(ConsoleColor.YELLOW + "Please, write full command\n" +
                    "Example: report projectName executorFirstName executorLastName" + ConsoleColor.RESET);
            return;
        }
        String[] commandArray = command.split(" ");
        Project project = projectController.findProject(commandArray[1]);
        User user = userController.findUser(commandArray[2], commandArray[3]);
        if (project != null && user != null) {
          taskController.showTask().stream()
                  .filter(task -> task.getProject().getId().equals(project.getId()) && task.getUserExecutor().getId().equals(user.getId()))
                  .collect(Collectors.toList())
                  .forEach(System.out :: println);
        } else System.out.printf(NOT_FOUND.getMessage() + "\n",
                USER.getSubject(),PROJECT.getSubject());
    }
}
