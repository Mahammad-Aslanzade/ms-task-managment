package com.example.msjira.model.task;

import com.example.msjira.enums.TaskStatus;
import com.example.msjira.model.reporter.ReporterWithoutTaskDto;
import com.example.msjira.model.teleSales.TeleSalesWithoutTaskDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskWithoutTeleSaleDto {
    private String id;
    private String subject;
    private String description;
    private ReporterWithoutTaskDto reporter;
    private TaskStatus status;
    private Date createdAt;
    private Date updatedAt;
    private Date deadline;
}
