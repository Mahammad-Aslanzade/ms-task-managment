package com.example.msjira.dao.entity;

import com.example.msjira.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String subject;
    private String description;
//    @CreationTimestamp
    private Date createdAt;
    private Date updatedAt;
    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "asignee_id")
    private TeleSalesEntity assignee;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private ReporterEntity reporter;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

}
