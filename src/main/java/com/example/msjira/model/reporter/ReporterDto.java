package com.example.msjira.model.reporter;

import com.example.msjira.model.task.TaskDto;
import com.example.msjira.model.task.TaskWithoutReporterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporterDto {
    private String id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String image;
    private Set<TaskWithoutReporterDto> task;
}
