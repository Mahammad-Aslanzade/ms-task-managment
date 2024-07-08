package com.example.msjira.dao.repository;

import com.example.msjira.dao.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity , String> {
    List<TaskEntity> findByAssigneeId(String assignee);
}
