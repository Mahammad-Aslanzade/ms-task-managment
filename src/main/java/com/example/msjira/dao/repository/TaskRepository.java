package com.example.msjira.dao.repository;

import com.example.msjira.dao.entity.TaskEntity;
import com.example.msjira.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity , String> {
    List<TaskEntity> findByAssigneeId(String assignee);

    List<TaskEntity> findAllBySubjectIsIgnoreCase(String subject);

    List<TaskEntity> findByDeadlineBeforeAndStatusIsNot(Date date , TaskStatus taskStatus);

    List<TaskEntity> findByAssigneeIdIsNull();
}
