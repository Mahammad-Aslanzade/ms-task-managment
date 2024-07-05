package com.example.msjira.dao.repository;

import com.example.msjira.dao.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity , String> {
}
