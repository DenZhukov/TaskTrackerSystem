package com.denzhukov.tasktrackersystem.service;

import com.denzhukov.tasktrackersystem.repository.entity.Task;

import java.util.List;

public interface TaskService {
    void create(Task task);

    void create(String[] message);

    void delete(Task task);

    List<Task> show();
}