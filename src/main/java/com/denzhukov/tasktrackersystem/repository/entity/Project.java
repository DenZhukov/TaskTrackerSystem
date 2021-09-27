package com.denzhukov.tasktrackersystem.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Data
@Entity
@Table(name = "Projects")
@EqualsAndHashCode(exclude = "tasksProject")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project", cascade = CascadeType.MERGE)
    private Collection<Task> tasksProject;

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
}
