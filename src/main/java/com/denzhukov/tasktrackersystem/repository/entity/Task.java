package com.denzhukov.tasktrackersystem.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn(name = "userHolder")
    private User userHolder;

    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn(name = "userExecutor")
    private User userExecutor;

    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn(name = "project")
    private Project project;

    @Override
    public String toString() {
        return id + ". " + name + " User Holder:" + userHolder.getFirstName() + " " + userHolder.getLastName()
                + " Project:" + project.getName() + " Executor:" + checkExecutor();
    }

    private String checkExecutor() {
        if (userExecutor != null)
            return userExecutor.getFirstName() + " " + userExecutor.getLastName();
        return "Executor has not been appointed yet";
    }
}
