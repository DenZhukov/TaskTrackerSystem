package com.denzhukov.tasktrackersystem.repository;

import com.denzhukov.tasktrackersystem.repository.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
