package com.example.msjira.dao.entity;

import com.example.msjira.enums.TeleSalePosition;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tele_sales")
public class TeleSalesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String surname;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private TeleSalePosition position;
    private LocalDate birthDate;
    private String image;
    @OneToMany(mappedBy = "assignee" )
    private Set<TaskEntity> tasks;

}
