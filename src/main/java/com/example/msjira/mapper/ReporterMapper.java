package com.example.msjira.mapper;

import com.example.msjira.dao.entity.ReporterEntity;
import com.example.msjira.model.reporter.ReporterCUDto;
import com.example.msjira.model.reporter.ReporterDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReporterMapper {

    List<ReporterDto> listToDto(List<ReporterEntity> reporterEntities);

    ReporterEntity cuDtoToEntity(ReporterCUDto reporterCUDto);
    ReporterDto mapToDto(ReporterEntity reporterEntity);

}
