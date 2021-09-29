package com.denzhukov.tasktrackersystem.console;

import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TaskCheck {
    private final TaskController taskController;

    @Autowired
    public TaskCheck(TaskController taskController) {
        this.taskController = taskController;
    }

    @PostConstruct
    public void close() {
        taskController.showTask().stream()
                .filter(task -> task.getDeadLine() != null && remainDays(task.getDeadLine()) < 1)
                .filter(this::activeSubTasks)
                .forEach(taskController :: delete);
    }

    private boolean activeSubTasks(Task task) {
        if(taskController.findSubTasks(task).size() == 0)
            return true;
        else return taskController.findSubTasks(task).stream().anyMatch(subtask -> subtask.getDeadLine() != null
                && remainDays(subtask.getDeadLine()) < 1);
    }

    public static long remainDays (Date deadLine) {
        Date current = new Date();
        long diff = deadLine.getTime() - current.getTime();
        return TimeUnit.MILLISECONDS.toDays(diff) + 1;
    }
}
