package com.denzhukov.tasktrackersystem.service;

import com.denzhukov.tasktrackersystem.repository.entity.Task;

import java.util.List;

public interface TaskService {
    void create(Task task);

    void create(String taskName, String fistNameUser, String lastNameUser, String projectName);

    void delete(Task task);

    List<Task> show();
}