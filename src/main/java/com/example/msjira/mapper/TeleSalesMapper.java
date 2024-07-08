package com.example.msjira.mapper;

import com.example.msjira.dao.entity.TeleSalesEntity;
import com.example.msjira.model.teleSales.TeleSaleDto;
import com.example.msjira.model.teleSales.TeleSaleReqDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeleSalesMapper {

    List<TeleSaleDto> listToDto(List<TeleSalesEntity> teleSalesEntities);

    TeleSaleDto mapToDto(TeleSalesEntity teleSalesEntity);
    TeleSalesEntity mapToEntity(TeleSaleReqDto teleSaleReqDto);
}
