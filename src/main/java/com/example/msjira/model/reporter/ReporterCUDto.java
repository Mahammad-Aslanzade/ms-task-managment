package com.example.msjira.model.reporter;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReporterCUDto {
    @NotNull(message = "name mustn't be null")
    private String name;
    @NotNull(message = "surname mustn't be null")
    private String surname;
    @NotNull(message = "birthDate mustn't be null")
    private LocalDate birthDate;
    private String image;
}
