package com.example.msjira.mapper;

import com.example.msjira.dao.entity.TaskEntity;
import com.example.msjira.model.task.TaskDto;
import com.example.msjira.model.task.TaskReqDto;
import com.example.msjira.model.task.TaskShortDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    List<TaskDto> listToDto(List<TaskEntity> taskEntities);

    TaskEntity mapToEntity(TaskReqDto taskReqDto);
    TaskDto mapToDto(TaskEntity taskEntity);
    TaskShortDto mapToShortDto(TaskEntity taskEntity);
}
