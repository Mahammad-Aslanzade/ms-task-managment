package com.example.msjira.model.task;

import com.example.msjira.enums.TaskStatus;
import com.example.msjira.model.reporter.ReporterWithoutTaskDto;
import com.example.msjira.model.teleSales.TeleSalesWithoutTaskDto;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskShortDto {
    private String id;
    private TeleSalesWithoutTaskDto assignee;
    private Date createdAt;
    private Date updatedAt;
    private Date deadline;
}
