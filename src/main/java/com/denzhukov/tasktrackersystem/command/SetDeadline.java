package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.pi4j.util.ConsoleColor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.denzhukov.tasktrackersystem.console.Messages.FULL_COMMAND;
import static com.denzhukov.tasktrackersystem.console.Messages.NOT_FOUND1;
import static com.denzhukov.tasktrackersystem.console.Subject.TASK;

public class SetDeadline implements Command{

    private final TaskController taskController;

    public SetDeadline(TaskController taskController) {
        this.taskController = taskController;
    }

    @Override
    public void execute(String command) {
        String[] commandArray = command.split(" ");
        if (commandArray.length != 4) {
            System.out.println(FULL_COMMAND.getMessage() +
            ConsoleColor.YELLOW + "\nExample: deadline taskName projectName 31.12.2021\n" + ConsoleColor.RESET);
            return;
        }
        Task task = taskController.findTask(commandArray[1], commandArray[2]);
        if (task != null) {
            String dateStr = commandArray[3];
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            try {
                //TODO check date
                Date deadLine = formatter.parse(dateStr);
                task.setDeadLine(deadLine);
                taskController.create(task);
                System.out.println(ConsoleColor.GREEN + "Deadline was set" + ConsoleColor.RESET);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else System.out.printf(NOT_FOUND1.getMessage(), TASK.getSubject());
    }
}
