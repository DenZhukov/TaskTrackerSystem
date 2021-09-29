package com.denzhukov.tasktrackersystem.service;

import com.denzhukov.tasktrackersystem.repository.TaskRepository;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.denzhukov.tasktrackersystem.repository.entity.User;
import com.pi4j.util.ConsoleColor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public Task findTask(String taskName, String projectName) {
        return show().stream()
                .filter(task1 -> task1.getName().equalsIgnoreCase(taskName) &&
                        task1.getProject().getName().equalsIgnoreCase(projectName))
                .findFirst().orElse(null);
    }

    @Override
    public Task create(String taskName, String fistNameUser, String lastNameUser, String projectName) {
        Task task = createShortVersion(taskName, fistNameUser, lastNameUser, projectName);
        if (task != null) {
            taskRepository.save(task);
            return task;
        } else return null;
    }

    @Override
    public Task create(String taskName, String fistNameUser, String lastNameUser, String projectName,
                       String taskParentName, Date deadLine) {
        Task task = createShortVersion(taskName, fistNameUser, lastNameUser, projectName);
        if (task != null) {
            task.setParentTask(findTask(taskParentName, projectName).getName());
            task.setDeadLine(deadLine);
            taskRepository.save(task);
            return task;
        } else return null;
    }

    @Override
    public List<Task> findSubTasks(Task task) {
        return show().stream().filter(subtask -> Objects.equals(subtask.getParentTask(), task.getName())
                        && Objects.equals(subtask.getProject(), task.getProject())).collect(Collectors.toList());
    }

    //delete a task and its subtask
    @Override
    public void delete(Task task) {
        List<Task> deleteList = findSubTasks(task);
        while (findSubTasks(task).size() > 0) {
            deleteList.forEach(this::delete);
        }
        taskRepository.deleteAll(findSubTasks(task));
        taskRepository.delete(task);
    }

    @Override
    public List<Task> show() {
        return taskRepository.findAll();
    }

    //for overloaded methods create(Task)
    private Task createShortVersion(String taskName, String fistNameUser, String lastNameUser, String projectName) {
        User userHolder = userService.findUser(fistNameUser, lastNameUser);
        Project project = projectService.show().stream()
                .filter(project1 -> project1.getName().equalsIgnoreCase(projectName))
                .findAny().orElse(null);
        if (userHolder != null && project != null) {
            Task task = new Task();
            task.setName(taskName);
            task.setUserExecutor(userHolder);
            task.setProject(project);
            project.addTask(task);
            userHolder.addTaskExecutor(task);
            return task;
        } else System.out.println(ConsoleColor.RED + "User or Project don't exist, please check list of users or project " +
                "(command \"show users(project)\")." + ConsoleColor.RESET);
        return null;
    }
}
