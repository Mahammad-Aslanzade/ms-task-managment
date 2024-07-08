package com.example.msjira.model.teleSales;

import com.example.msjira.enums.TeleSalePosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeleSaleReqDto {
    private String name;
    private String surname;
    private TeleSalePosition position;
    private LocalDate birthDate;
    private String image;
}
