package com.example.msjira.mapper;

import com.example.msjira.dao.entity.TeleSalesEntity;
import com.example.msjira.model.teleSales.TeleSaleDto;
import com.example.msjira.model.teleSales.TeleSaleReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TeleSalesMapper {

    List<TeleSaleDto> listToDto(List<TeleSalesEntity> teleSalesEntities);

    TeleSaleDto mapToDto(TeleSalesEntity teleSalesEntity);
    TeleSalesEntity mapToEntity(TeleSaleReqDto teleSaleReqDto);
}
