package com.example.msjira.model.task;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;

}