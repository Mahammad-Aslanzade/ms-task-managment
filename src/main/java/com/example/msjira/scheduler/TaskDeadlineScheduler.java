package com.example.msjira.scheduler;

import com.example.msjira.dao.entity.TaskEntity;
import com.example.msjira.dao.entity.TeleSalesEntity;
import com.example.msjira.dao.repository.TaskRepository;
import com.example.msjira.dao.repository.TeleSalesRepository;
import com.example.msjira.enums.TaskStatus;
import com.example.msjira.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class TaskDeadlineScheduler {

    private final EmailService emailService;
    private final TaskRepository taskRepository;

    @Scheduled(fixedRate = 5000)
    public void informDeadlineDate() {
        log.info("ACTION.informDeadlineDate.start");
        Date now = new Date(System.currentTimeMillis());
        List<TaskEntity> taskEntities = taskRepository.findByDeadlineBeforeAndStatusIsNot(now, TaskStatus.EXPIRED);
        taskEntities.forEach((task) -> {
                    task.setStatus(TaskStatus.EXPIRED);
                    taskRepository.save(task);
                    TeleSalesEntity assignee = task.getAssignee();
                    emailService.postEmail(
                            assignee.getEmail(),
                            "Task Status",
                            "Your Task ( " + task.getSubject() + " ) has already Expired!"
                    );
                }
        );
        log.info("ACTION.informDeadlineDate.end");
    }


}
