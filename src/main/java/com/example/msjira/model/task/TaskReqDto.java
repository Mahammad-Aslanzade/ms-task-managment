package com.example.msjira.model.task;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskReqDto {
    @NotNull(message = "subject mustn't be null")
    private String subject;
    @NotNull(message = "description mustn't be null")
    private String description;
    @NotNull(message = "reporterId mustn't be null")
    private String reporterId;
    private String assigneeId;

}