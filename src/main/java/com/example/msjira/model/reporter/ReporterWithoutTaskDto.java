package com.example.msjira.model.reporter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporterWithoutTaskDto {

    private String id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String image;
}
