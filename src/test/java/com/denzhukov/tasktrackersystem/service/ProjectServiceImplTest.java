package com.denzhukov.tasktrackersystem.service;

import com.denzhukov.tasktrackersystem.repository.ProjectRepository;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class ProjectServiceImplTest {

    private ProjectService projectService;
    private ProjectRepository projectRepository;
    private Project project;
    private Project project2;

    @BeforeEach
    public void init() {
        projectRepository = Mockito.mock(ProjectRepository.class);
        projectService = new ProjectServiceImpl(projectRepository);
        project = new Project();
        project.setName("Project");
        projectService.create(project);
    }

    @Test
    void create() {

        Mockito.verify(projectRepository).save(project);
    }

    @Test
    void delete() {
        projectService.delete(project);

        Mockito.verify(projectRepository).delete(project);
    }

    @Test
    void show() {
        List<Project> tasks = projectService.show();

        Mockito.verify(projectRepository).findAll();
    }
}