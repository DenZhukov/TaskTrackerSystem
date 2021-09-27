package com.denzhukov.tasktrackersystem.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Data
@Entity
@Table(name = "Users")
@EqualsAndHashCode(exclude = "tasksHolder")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userHolder")
    private Collection<Task> tasksHolder;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userExecutor")
    private Collection<Task> tasksExecutor;

    @Override
    public String toString() {
        return id + ". " + firstName + " " + lastName;
    }

    public void addTaskHolder(Task task) {
        if (tasksHolder == null) {
            tasksHolder = new HashSet<>();
        }
        tasksHolder.add(task);
    }

    public void addTaskExecutor(Task task) {
        if (tasksExecutor == null) {
            tasksExecutor = new HashSet<>();
        }
        tasksExecutor.add(task);
    }
}
