package com.example.msjira.model.teleSales;

import com.example.msjira.enums.TeleSalePosition;
import com.example.msjira.model.task.TaskWithoutTeleSaleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeleSaleDto {
    private String id;
    private String name;
    private String surname;
    private TeleSalePosition position;
    private LocalDate birthDate;
    private String image;
    private List<TaskWithoutTeleSaleDto> tasks;
}
