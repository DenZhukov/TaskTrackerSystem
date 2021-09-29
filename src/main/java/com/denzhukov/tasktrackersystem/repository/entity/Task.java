package com.denzhukov.tasktrackersystem.repository.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn(name = "userExecutor")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User userExecutor;

    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn(name = "project")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    @Column(name = "parentTask")
    private String parentTask;

    @Column(name = "deadline")
    private Date deadLine;

    @Override
    public String toString() {
        return id + ". " + name +
                 " Project:" + project.getName() + " Executor:" + checkExecutor();
    }

    private String checkExecutor() {
        if (userExecutor != null)
            return userExecutor.getFirstName() + " " + userExecutor.getLastName();
        return "Executor has not been appointed yet";
    }
}
