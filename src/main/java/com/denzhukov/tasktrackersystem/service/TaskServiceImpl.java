package com.denzhukov.tasktrackersystem.service;

import com.denzhukov.tasktrackersystem.repository.TaskRepository;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.denzhukov.tasktrackersystem.repository.entity.User;
import com.pi4j.util.ConsoleColor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
        User user = userService.findUser(fistNameUser, lastNameUser);
        Project project = projectService.show().stream()
                .filter(project1 -> project1.getName().equalsIgnoreCase(projectName))
                .findAny().orElse(null);
        if (user != null && project != null) {
            Task task = new Task();
            task.setName(taskName);
            task.setUserExecutor(user);
            task.setProject(project);
            project.addTask(task);
            user.addTaskExecutor(task);
            return task;
        } else System.out.println(ConsoleColor.RED + "User or Project don't exist, please check list of users or project " +
                "(command \"show users(project)\")." + ConsoleColor.RESET);
        return null;
    }

    //create special print format for tasks
    @Override
    public void printTasks(List<Task> tasks) {
        System.out.format("%-2s|%-10s|%-15s|%-7s%-10s|%-25s\n", "Id", "Name", "Project", "User", "Executor", "Deadline");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        for (Task task : tasks) {
            Date deadline = task.getDeadLine();
            if (deadline != null) {
                printAndCheckDeadline(task.getId(), task.getName(), task.getProject().getName(),
                        task.getUserExecutor().getFirstName(), task.getUserExecutor().getLastName(),
                        format.format(deadline));
            } else printAndCheckDeadline(task.getId(), task.getName(), task.getProject().getName(),
                    task.getUserExecutor().getFirstName(), task.getUserExecutor().getLastName(),
                    "deadline was not set");
        }
    }

    private void printAndCheckDeadline(Integer id, String taskName, String projectName, String firstName,
                                       String lastName, String deadline) {
        System.out.format("%-2d|%-10s|%-15s|%-7s%-10s|%-25s\n",
                id, taskName, projectName, firstName, lastName, deadline);
    }
}
