package com.denzhukov.tasktrackersystem.service;

import com.denzhukov.tasktrackersystem.repository.TaskRepository;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.denzhukov.tasktrackersystem.repository.entity.User;
import com.pi4j.util.ConsoleColor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ProjectService projectService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService, ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.projectService = projectService;
    }

    @Override
    public void create(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void create(String taskName, String fistNameUser, String lastNameUser, String projectName) {
        User userHolder = userService.show().stream()
                .filter(user -> (user.getFirstName() + " " + user.getLastName()).equalsIgnoreCase(fistNameUser + " " + lastNameUser))
                .findAny().orElse(null);
        Project project = projectService.show().stream()
                .filter(project1 -> project1.getName().equalsIgnoreCase(projectName))
                .findAny().orElse(null);
        if (userHolder != null && project != null) {
            Task task = new Task();
            task.setId(setTaskID());
            task.setName(taskName);
            task.setUserHolder(userHolder);
            task.setProject(project);
            project.addTask(task);
            userHolder.addTaskHolder(task);
            taskRepository.save(task);
        } else System.out.println(ConsoleColor.RED + "User or Project don't exist, please check list of users or project " +
                "(command \"show users(project)\")." + ConsoleColor.RESET);
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public List<Task> show() {
        return taskRepository.findAll();
    }


    private int setTaskID() {
        List<Task> tasks = show();
        int size = tasks.size();
        if(size == 0)
            return 1;
        else return tasks.stream().skip(tasks.size() - 1).findAny().get().getId() + 1;
    }
}
