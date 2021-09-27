package com.denzhukov.tasktrackersystem.console;

import com.denzhukov.tasktrackersystem.command.CommandContainer;
import com.denzhukov.tasktrackersystem.repository.ProjectRepository;
import com.denzhukov.tasktrackersystem.repository.TaskRepository;
import com.denzhukov.tasktrackersystem.repository.UserRepository;
import com.denzhukov.tasktrackersystem.repository.entity.Project;
import com.denzhukov.tasktrackersystem.repository.entity.Task;
import com.denzhukov.tasktrackersystem.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.denzhukov.tasktrackersystem.command.CommandName.EXIT;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CommandContainer commandContainer;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ConsoleRunner(CommandContainer commandContainer, UserRepository userRepository,
                         ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.commandContainer = commandContainer;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        long count = userRepository.count();
        if(count == 0) {
            User user1 = new User();
            user1.setFirstName("Den");
            user1.setLastName("Zhukov");
            userRepository.save(user1);

            User user2 = new User();
            user2.setFirstName("Ann");
            user2.setLastName("Ivanova");
            userRepository.save(user2);

            Project project = new Project();
            project.setName("Tracker");
            projectRepository.save(project);

            Task task = new Task();
            task.setId(1);
            task.setName("First");
            task.setUserHolder(user1);
            user1.addTaskHolder(task);
            task.setProject(project);
            project.addTask(task);
            taskRepository.save(task);

            Task task2 = new Task();
            task2.setId(2);
            task2.setName("Second");
            task2.setUserHolder(user1);
            user1.addTaskHolder(task2);
            task2.setProject(project);
            project.addTask(task2);
            taskRepository.save(task2);

        }
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String command = "";
            while (!command.equalsIgnoreCase(EXIT.getCommandName())) {
                command = reader.readLine();
                commandContainer.findCommand(command.split(" ")[0]).execute(command);
            }
        }catch(IOException e){
                e.printStackTrace();
        }
    }
}
