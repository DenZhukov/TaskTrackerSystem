package com.denzhukov.tasktrackersystem.service;

import com.denzhukov.tasktrackersystem.repository.entity.Task;

import java.util.Date;
import java.util.List;

public interface TaskService {
    void create(Task task);

    Task create(String taskName, String fistNameUser, String lastNameUser, String projectName);

    Task create(String taskName, String fistNameUser, String lastNameUser, String projectName,
                String taskParentName, Date deadLine);

    List<Task> findSubTasks (Task task);

    void delete(Task task);

    List<Task> show();

    Task findTask(String taskName, String projectName);

    void printTasks(List<Task> tasks);
}