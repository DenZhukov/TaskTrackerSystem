package com.denzhukov.tasktrackersystem.repository.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Override
    public String toString() {
        return id + ". " + firstName + " " + lastName;
    }
}
