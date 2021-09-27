package com.denzhukov.tasktrackersystem.controller;

import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.denzhukov.tasktrackersystem.service.TaskService;
import org.springframework.stereotype.Controller;

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

    public void create(String[] commandArray) { taskService.create(commandArray); }

    public void delete(Task task) {
        taskService.delete(task);
    }

}
