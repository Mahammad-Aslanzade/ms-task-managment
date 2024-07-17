package com.example.msjira.controller;

import com.example.msjira.model.task.TaskDto;
import com.example.msjira.model.task.TaskReqDto;
import com.example.msjira.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public TaskDto getTaskById(@PathVariable String taskId){
        return taskService.getTaskById(taskId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@Valid @RequestBody TaskReqDto taskReqDto){
        taskService.createTask(taskReqDto);
    }

    @PutMapping("/{taskId}")
    public void updateTask(@Valid @RequestBody TaskReqDto taskReqDto , @PathVariable String taskId){
        taskService.updateTask(taskId ,taskReqDto);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable String taskId){
        taskService.deleteTask(taskId);
    }

}
