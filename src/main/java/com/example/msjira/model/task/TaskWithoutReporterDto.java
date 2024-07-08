package com.example.msjira.model.task;

import com.example.msjira.enums.TaskStatus;
import com.example.msjira.model.teleSales.TeleSalesWithoutTaskDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskWithoutReporterDto {
    private String id;
    private String subject;
    private String description;
    private TeleSalesWithoutTaskDto assignee;
    private TaskStatus status;
    private Date createdAt;
    private Date updatedAt;
    private Date deadline;
}
