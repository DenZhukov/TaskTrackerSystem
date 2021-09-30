package com.denzhukov.tasktrackersystem.controller;

import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.denzhukov.tasktrackersystem.service.TaskService;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public List<Task> showTask() {
        return taskService.show();
    }

    public void create(Task task) {
        taskService.create(task);
    }

    public Task create(String taskName, String fistNameUser, String lastNameUser, String projectName) {
       return taskService.create(taskName, fistNameUser, lastNameUser, projectName); }

    public Task create(String taskName, String fistNameUser, String lastNameUser, String projectName, String taskParentName, Date deadLine) {
        return taskService.create(taskName, fistNameUser, lastNameUser, projectName, taskParentName, deadLine); }

    public List<Task> findSubTasks(Task task) {
        return taskService.findSubTasks(task);
    }

    public void delete(Task task) {
        taskService.delete(task);
    }

    public Task findTask(String taskName, String projectName) {
        return taskService.findTask(taskName, projectName);
    }

    public void printTasks(List<Task> tasks) {
        taskService.printTasks(tasks);
    }

}
