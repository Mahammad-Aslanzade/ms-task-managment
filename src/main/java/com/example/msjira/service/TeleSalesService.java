package com.example.msjira.service;

import com.example.msjira.dao.entity.TaskEntity;
import com.example.msjira.dao.entity.TeleSalesEntity;
import com.example.msjira.dao.repository.TaskRepository;
import com.example.msjira.dao.repository.TeleSalesRepository;
import com.example.msjira.enums.ExceptionMessages;
import com.example.msjira.exceptions.NotFoundException;
import com.example.msjira.mapper.TeleSalesMapper;
import com.example.msjira.model.teleSales.TeleSaleDto;
import com.example.msjira.model.teleSales.TeleSaleReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeleSalesService {

    private final TeleSalesRepository teleSalesRepository;
    private final TeleSalesMapper teleSalesMapper;
    private final TaskRepository taskRepository;


    public List<TeleSaleDto> getAllTeleSales() {
        log.info("ACTION.getAllTeleSales.start");
        List<TeleSalesEntity> teleSalesEntityList = teleSalesRepository.findAll();
        List<TeleSaleDto> teleSaleDtoList = teleSalesMapper.listToDto(teleSalesEntityList);
        log.info("ACTION.getAllTeleSales.end");
        return teleSaleDtoList;
    }

    private TeleSalesEntity findTeleSaleEntityById(String teleSaleId) {
        return teleSalesRepository.findById(teleSaleId).orElseThrow(() ->
                new NotFoundException(
                        ExceptionMessages.TELESALE_NOT_FOUND.message(),
                        ExceptionMessages.TASK_NOT_FOUND.createLog("findTeleSaleById", teleSaleId)
                )
        );
    }

    private TaskEntity findTaskEntityById(String taskId) {
        return taskRepository.findById(taskId).orElseThrow(() ->
                new NotFoundException(
                        ExceptionMessages.TASK_NOT_FOUND.message(),
                        ExceptionMessages.TASK_NOT_FOUND.createLog("findTaskEntityById", taskId)
                )
        );
    }


    public TeleSaleDto getTeleSaleById(String teleSaleId) {
        log.info("ACTION.getTeleSaleById.start teleSaleId : {} ", teleSaleId);
        TeleSalesEntity teleSalesEntity = findTeleSaleEntityById(teleSaleId);
        TeleSaleDto teleSaleDto = teleSalesMapper.mapToDto(teleSalesEntity);
        log.info("ACTION.getTeleSaleById.end teleSaleId : {} ", teleSaleId);
        return teleSaleDto;
    }

    public void createTeleSale(TeleSaleReqDto teleSaleReqDto) {
        log.info("ACTION.createTeleSale.start teleSaleDto : {} ", teleSaleReqDto);
        TeleSalesEntity teleSalesEntity = teleSalesMapper.mapToEntity(teleSaleReqDto);
        teleSalesRepository.save(teleSalesEntity);
        log.info("ACTION.createTeleSale.end teleSaleDto : {} ", teleSaleReqDto);
    }

    public void updateTeleSale(String teleSaleId, TeleSaleReqDto teleSaleReqDto) {
        log.info("ACTION.updateTeleSale.start teleSaleId : {} , reqBody : {}", teleSaleId, teleSaleReqDto);
        TeleSalesEntity teleSale = findTeleSaleEntityById(teleSaleId);
        TeleSalesEntity updatedTeleSale = teleSalesMapper.mapToEntity(teleSaleReqDto);
        updatedTeleSale.setId(teleSale.getId());
        updatedTeleSale.setTasks(teleSale.getTasks());
        teleSalesRepository.save(updatedTeleSale);
        log.info("ACTION.updateTeleSale.end teleSaleId : {} , reqBody : {}", teleSaleId, teleSaleReqDto);
    }

    public void deleteTeleSale(String teleSaleId) {
        log.info("ACTION.deleteTeleSale.start teleSaleId : {}", teleSaleId);
        TeleSalesEntity deletedTeleSale = findTeleSaleEntityById(teleSaleId);
        List<TaskEntity> tasks = taskRepository.findByAssigneeId(teleSaleId);
        tasks.forEach((t) -> {
            t.setAssignee(null);
        });
        taskRepository.saveAll(tasks);
        teleSalesRepository.delete(deletedTeleSale);
        log.info("ACTION.deleteTeleSale.end teleSaleId : {}", teleSaleId);
    }

    public void unAssignTask(String teleSaleId, String taskId) {
        log.info("ACTION.unAssignTask.start teleSaleId : {} taskId : {}", teleSaleId, taskId);
        TeleSalesEntity teleSale = findTeleSaleEntityById(teleSaleId);
        TaskEntity task = findTaskEntityById(taskId);

        if(teleSalesRepository.existsTaskForTeleSale(teleSaleId,taskId)){
            task.setAssignee(null);
            taskRepository.save(task);
        }else{
            throw new NotFoundException(
                    ExceptionMessages.TELESALE_TASK_NOT_FOUND.message(),
                    ExceptionMessages.TELESALE_TASK_NOT_FOUND.createLog("unAssignTask",taskId)
                    );
        }
        log.info("ACTION.unAssignTask.end teleSaleId : {} taskId : {}", teleSaleId, taskId);
    }
}
