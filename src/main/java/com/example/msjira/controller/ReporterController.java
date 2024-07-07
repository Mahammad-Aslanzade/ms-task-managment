package com.example.msjira.controller;

import com.example.msjira.model.reporter.ReporterCUDto;
import com.example.msjira.model.reporter.ReporterDto;
import com.example.msjira.service.ReporterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/reporter")
@RequiredArgsConstructor
public class ReporterController {

    private final ReporterService reporterService;

    @GetMapping
    public List<ReporterDto> getAllReporters(){
        return reporterService.getAllReporters();
    }

    @GetMapping("/{reporterId}")
    public ReporterDto getReporterById(@PathVariable String reporterId){
        return reporterService.getReporterById(reporterId);
    }

    @PostMapping
    public void createReporter(@Valid @RequestBody ReporterCUDto reporterCUDto){
        reporterService.createReporter(reporterCUDto);
    }

    @PutMapping("/{reporterId}")
    public void updateReporter(@Valid @RequestBody ReporterCUDto reporterCUDto , @PathVariable String reporterId){
        reporterService.updateReporter(reporterCUDto , reporterId);
    }

    @DeleteMapping("/{reporterId}")
    public void deleteReporter(@PathVariable String reporterId){
        reporterService.deleteReporter(reporterId);
    }


}
