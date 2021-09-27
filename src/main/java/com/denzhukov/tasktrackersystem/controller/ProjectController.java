package com.denzhukov.tasktrackersystem.controller;

import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.service.ProjectService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public List<Project> showProjects() {
        return projectService.show();
    }

    public void create(Project project) {
        projectService.create(project);
    }

    public void delete(Project project) {
        projectService.delete(project);
    }
}