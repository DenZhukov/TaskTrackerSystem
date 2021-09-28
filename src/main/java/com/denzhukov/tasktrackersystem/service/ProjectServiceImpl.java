package com.denzhukov.tasktrackersystem.service;

import com.denzhukov.tasktrackersystem.repository.ProjectRepository;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.pi4j.util.ConsoleColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void create(Project project) {
        projectRepository.save(project);
    }

    @Override
    public boolean create(String nameProject) {
        if (show().stream().noneMatch(project -> project.getName().equalsIgnoreCase(nameProject))) {
            Project project = new Project();
            project.setName(nameProject);
            create(project);
            return true;
        } else {
            System.out.printf(ConsoleColor.RED + "%s already exist" + ConsoleColor.RESET, nameProject);
            return false;
        }
    }

    @Override
    public void delete(Project project) {
        projectRepository.delete(project);
    }

    @Override
    public List<Project> show() {
        return projectRepository.findAll();
    }
}
