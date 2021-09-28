package com.denzhukov.tasktrackersystem.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Data
@Entity
@Table(name = "Users")
@EqualsAndHashCode(exclude = {"tasksExecutor", "projects"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userExecutor")
    private Collection<Task> tasksExecutor;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    List<Project> projects;

    @Override
    public String toString() {
        return id + ". " + firstName + " " + lastName;
    }

    public void addTaskExecutor(Task task) {
        if (tasksExecutor == null) {
            tasksExecutor = new HashSet<>();
        }
        tasksExecutor.add(task);
    }
}
