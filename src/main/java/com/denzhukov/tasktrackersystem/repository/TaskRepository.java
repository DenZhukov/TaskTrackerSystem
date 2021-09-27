package com.denzhukov.tasktrackersystem.repository;

import com.denzhukov.tasktrackersystem.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
