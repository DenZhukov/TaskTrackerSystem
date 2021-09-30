package com.denzhukov.tasktrackersystem.service;

import com.denzhukov.tasktrackersystem.repository.TaskRepository;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.denzhukov.tasktrackersystem.repository.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class TaskServiceImplTest {

    private TaskService taskService;
    private TaskRepository taskRepository;
    private UserService userService;
    private ProjectService projectService;

    @BeforeEach
    public void init() {
        taskRepository = Mockito.mock(TaskRepository.class);
        userService = Mockito.mock(UserService.class);
        projectService = Mockito.mock(ProjectService.class);
        taskService = new TaskServiceImpl(taskRepository, userService, projectService);
    }

    @Test
    public void create() {
        Project project = new Project();
        project.setName("Project");
        projectService.create(project);
        User user = new User();
        user.setFirstName("User1");
        user.setLastName("User2");
        userService.create(user);

        Task task = new Task();
        task.setName("Task");
        task.setProject(project);
        task.setUserExecutor(user);
        project.addTask(task);
        user.addTaskExecutor(task);
        taskService.create(task);

        Mockito.verify(taskRepository).save(task);
    }

    @Test
    public void show() {
        List<Task> tasks = taskService.show();
        Mockito.verify(taskRepository).findAll();
    }


}
