package com.example.msjira.service;

import com.example.msjira.dao.entity.ReporterEntity;
import com.example.msjira.dao.entity.TaskEntity;
import com.example.msjira.dao.entity.TeleSalesEntity;
import com.example.msjira.dao.repository.ReporterRepository;
import com.example.msjira.dao.repository.TaskRepository;
import com.example.msjira.dao.repository.TeleSalesRepository;
import com.example.msjira.enums.ExceptionMessages;
import com.example.msjira.enums.TaskStatus;
import com.example.msjira.exceptions.NotFoundException;
import com.example.msjira.mapper.TaskMapper;
import com.example.msjira.model.task.TaskDto;
import com.example.msjira.model.task.TaskReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskMapper taskMapper;

    private final TaskRepository taskRepository;
    private final ReporterRepository reporterRepository;
    private final TeleSalesRepository teleSalesRepository;

    public List<TaskDto> getAllTasks() {
        log.info("ACTION.getAllTasks.start");
        List<TaskEntity> taskEntities = taskRepository.findAll();
        List<TaskDto> taskDtos = taskMapper.listToDto(taskEntities);
        log.info("ACTION.getAllTasks.start");
        return taskDtos;
    }

    public TaskDto getTaskById(String taskId) {
        log.info("ACTION.getTaskById.start taskId : {}", taskId);
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(() ->
                new NotFoundException(
                        ExceptionMessages.TASK_NOT_FOUND.message(),
                        ExceptionMessages.TASK_NOT_FOUND.createLog("findTaskById", taskId)
                ));
        TaskDto taskDto = taskMapper.mapToDto(taskEntity);
        log.info("ACTION.getTaskById.start taskId : {}", taskId);
        return taskDto;
    }

    private TeleSalesEntity findTeleSale(String teleSalesId) {
        return teleSalesRepository.findById(teleSalesId)
                .orElseThrow(() ->
                        new NotFoundException(
                                ExceptionMessages.TELESALE_NOT_FOUND.message(),
                                ExceptionMessages.TELESALE_NOT_FOUND.createLog("findTeleSale", teleSalesId)
                        )
                );
    }

    private ReporterEntity findReporter(String reporterId) {
        return reporterRepository.findById(reporterId)
                .orElseThrow(() ->
                        new NotFoundException(
                                ExceptionMessages.REPORTER_NOT_FOUND.message(),
                                ExceptionMessages.REPORTER_NOT_FOUND.createLog("findReporter", reporterId)
                        )
                );
    }


    private TaskEntity sameSubjectAndRecently(List<TaskEntity> sameSubjectTask , String subject){
        if (!sameSubjectTask.isEmpty()) {
            TaskEntity recentlyCreated = sameSubjectTask.get(0);
            for (TaskEntity task : sameSubjectTask) {
                if (task.getAssignee() != null) {
                    long difference = Math.abs(task.getCreatedAt().getTime() - System.currentTimeMillis());
                    long minDiff = Math.abs(recentlyCreated.getCreatedAt().getTime() - System.currentTimeMillis());
                    if (difference < minDiff) {
                        recentlyCreated = task;
                    }
                }
            }
            if(recentlyCreated.getAssignee() != null){
                return  recentlyCreated;
            }else{
                return null;
            }
        }
        return null;
    }

    public void createTask(TaskReqDto taskReqDto) {
        log.info("ACTION.createTask.start reqBody : {}", taskReqDto);
        ReporterEntity reporterEntity = findReporter(taskReqDto.getReporterId());

        TaskEntity taskEntity = taskMapper.mapToEntity(taskReqDto);

        if (taskReqDto.getStatus() == null) {
            taskEntity.setStatus(TaskStatus.TODO);
        }

        if (taskReqDto.getAssigneeId() != null) {
            TeleSalesEntity asignee = findTeleSale(taskReqDto.getAssigneeId());
            taskEntity.setAssignee(asignee);
        } else {
            // automatically assign task

            // burda eyni subjectde olan ve
            // en axirinci yaradilib ve assign olunmus taski tapir
            List<TaskEntity> sameSubjectTask = taskRepository.findAllBySubjectIsIgnoreCase(taskReqDto.getSubject());
            TaskEntity lastCreatedAndAssignedSameSubject = sameSubjectAndRecently(sameSubjectTask , taskReqDto.getSubject());


            if(lastCreatedAndAssignedSameSubject != null){
                taskEntity.setAssignee(lastCreatedAndAssignedSameSubject.getAssignee());
            }else{
                // 2nd case
                Optional<TeleSalesEntity> findFewestTaskAssignee = teleSalesRepository.findTeleSalesWithFewestTasksExcludingDone();
                findFewestTaskAssignee.ifPresent(taskEntity::setAssignee);
                System.out.println(findFewestTaskAssignee.get().getName());
            }

        }

        // task entity settings
        taskEntity.setReporter(reporterEntity);
        taskEntity.setCreatedAt(new Date(System.currentTimeMillis()));
        taskEntity.setStatus(TaskStatus.TODO);
        taskRepository.save(taskEntity);
        //
        log.info("ACTION.createTask.end reqBody : {}", taskReqDto);
    }

    public void updateTask(String taskId, TaskReqDto taskReqDto) {
        log.info("ACTION.updateTask.start reqBody : {}", taskReqDto);
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(() ->
                new NotFoundException(
                        ExceptionMessages.TASK_NOT_FOUND.message(),
                        ExceptionMessages.TASK_NOT_FOUND.createLog("findTaskById", taskId)
                )
        );
        TaskEntity updatedTaskEntity = taskMapper.mapToEntity(taskReqDto);
        updatedTaskEntity.setId(taskEntity.getId());
        updatedTaskEntity.setCreatedAt(taskEntity.getCreatedAt());

        TeleSalesEntity assignee = taskEntity.getAssignee();

        if (taskReqDto.getAssigneeId() != null) {
            assignee = findTeleSale(taskReqDto.getAssigneeId());
        }

        ReporterEntity reporter = findReporter(taskReqDto.getReporterId());

        updatedTaskEntity.setUpdatedAt(new Date(System.currentTimeMillis()));
        updatedTaskEntity.setAssignee(assignee);
        updatedTaskEntity.setReporter(reporter);
        taskRepository.save(updatedTaskEntity);

        log.info("ACTION.updateTask.end reqBody : {}", taskReqDto);
    }

    public void deleteTask(String taskId) {
        log.info("ACTION.deleteTask.start taskId : {}", taskId);
        taskRepository.deleteById(taskId);
        log.info("ACTION.deleteTask.end taskId : {}", taskId);
    }
}
