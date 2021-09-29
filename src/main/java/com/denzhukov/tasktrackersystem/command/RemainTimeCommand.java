package com.denzhukov.tasktrackersystem.command;

import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.repository.entity.Task;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.denzhukov.tasktrackersystem.command.CommandName.REMAIN;
import static com.denzhukov.tasktrackersystem.console.Messages.FULL_COMMAND;
import static com.denzhukov.tasktrackersystem.console.Messages.NOT_FOUND1;
import static com.denzhukov.tasktrackersystem.console.Subject.TASK;

public class RemainTimeCommand implements Command {
    private final TaskController taskController;

    public RemainTimeCommand(TaskController taskController) {
        this.taskController = taskController;
    }

    @Override
    public void execute(String command) {
        if (command.equalsIgnoreCase(REMAIN.getCommandName())) {
            remainDaysAllTasks();
            return;
        }
        String[] commandArray = command.split(" ");
        if (commandArray.length == 3) {
            Task task = taskController.findTask(commandArray[1], commandArray[2]);
            if (task != null) {
                if (task.getDeadLine() != null) {
                    System.out.println("Remain days: " + remainDays(task.getDeadLine()));
                    findSubTasks(task);
                } else {
                    System.out.println("Deadline was not set for this task");
                    findSubTasks(task);
                }
            } else System.out.printf(NOT_FOUND1.getMessage(), TASK.getSubject());
        }
        else System.out.println(FULL_COMMAND.getMessage());
    }


    private long remainDays (Date deadLine) {
        Date current = new Date();
        long diff = deadLine.getTime() - current.getTime();
        return TimeUnit.MILLISECONDS.toDays(diff) + 1;
    }

    private void remainDaysAllTasks() {
        List<Task> list = taskController.showTask().stream()
                .filter(task -> task.getDeadLine() != null).sorted(new TaskComparator()).collect(Collectors.toList());
        list.stream().map(task -> task.getId() + ". " + task.getName() + "-" + task.getProject().getName()
                + " Remain days:" + remainDays(task.getDeadLine())).forEach(System.out :: println);
    }

    private void findSubTasks (Task task) {
        List<Task> subtasks = taskController.showTask().stream()
                .filter(subtask -> Objects.equals(subtask.getParentTask(), task.getName())
                        && Objects.equals(subtask.getProject(), task.getProject()))
                .sorted(new TaskComparator())
                .collect(Collectors.toList());
        if (subtasks.size() > 0) {
            System.out.println("Subtasks deadlines:");
            for (Task subtask : subtasks) {
                if (subtask.getDeadLine() != null)
                System.out.println(subtask.getName() + ". Remain days: " + remainDays(subtask.getDeadLine()));
            }
        } else System.out.println("This task doesn't have subtasks");
    }
}

class TaskComparator implements Comparator<Task> {

    public int compare(Task o1, Task o2) {

        return o1.getDeadLine().compareTo(o2.getDeadLine());
    }
}