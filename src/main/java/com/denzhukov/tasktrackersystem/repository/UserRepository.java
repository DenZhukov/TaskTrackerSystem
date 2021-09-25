package com.denzhukov.tasktrackersystem.repository;

import com.denzhukov.tasktrackersystem.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
