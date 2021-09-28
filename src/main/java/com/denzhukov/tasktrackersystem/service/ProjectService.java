package com.denzhukov.tasktrackersystem.service;

import com.denzhukov.tasktrackersystem.repository.entity.Project;

import java.util.List;

public interface ProjectService {
    void create(Project project);

    boolean create(String nameProject);

    void delete(Project project);

    List<Project> show();
}
