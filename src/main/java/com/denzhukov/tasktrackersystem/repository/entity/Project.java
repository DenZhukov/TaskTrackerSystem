package com.denzhukov.tasktrackersystem.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Data
@Entity
@Table(name = "Projects")
@EqualsAndHashCode(exclude = {"tasksProject", "users"})
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    private Collection<Task> tasksProject;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
                name = "projects_x_users",
                joinColumns = @JoinColumn(name = "project_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @Override
    public String toString() {
        return id + ". " + name;
    }

    public void addTask(Task task) {
        if (tasksProject == null) {
            tasksProject = new HashSet<>();
        }
        tasksProject.add(task);
    }

    public void addUser(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }
}
