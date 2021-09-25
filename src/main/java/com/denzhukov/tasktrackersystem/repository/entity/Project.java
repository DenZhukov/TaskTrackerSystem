package com.denzhukov.tasktrackersystem.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return id + ". " + name;
    }
}
