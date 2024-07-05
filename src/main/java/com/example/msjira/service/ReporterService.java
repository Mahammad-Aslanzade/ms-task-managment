package com.example.msjira.service;

import com.example.msjira.dao.entity.ReporterEntity;
import com.example.msjira.dao.repository.ReporterRepository;
import com.example.msjira.enums.ExceptionMessages;
import com.example.msjira.exceptions.NotFoundException;
import com.example.msjira.mapper.ReporterMapper;
import com.example.msjira.model.reporter.ReporterCUDto;
import com.example.msjira.model.reporter.ReporterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReporterService {

    private final ReporterRepository reporterRepository;
    private final ReporterMapper reporterMapper;

    public List<ReporterDto> getAllReporters() {
        log.info("Action.getAllReporters.start");
        List<ReporterEntity> reporterEntities = reporterRepository.findAll();
        List<ReporterDto> reporterDtos = reporterMapper.listToDto(reporterEntities);
        log.info("Action.getAllReporters.end");
        return reporterDtos;
    }

    private ReporterEntity findReporterById(String reporterId){
        return reporterRepository.findById(reporterId)
                .orElseThrow(() ->
                        new NotFoundException(
                                ExceptionMessages.REPORTER_NOT_FOUND.message(),
                                ExceptionMessages.REPORTER_NOT_FOUND.createLog("updateReporter", reporterId)
                        )
                );
    }

    public ReporterDto getReporterById(String reporterId){
        log.info("Action.getReporterById.start reporterId : {}", reporterId);
        ReporterEntity reporterEntity = findReporterById(reporterId);
        ReporterDto reporterDto = reporterMapper.mapToDto(reporterEntity);
        log.info("Action.getReporterById.end reporterId : {}", reporterId);
        return reporterDto;
    }


    public void createReporter(ReporterCUDto reporterCUDto) {
        log.info("Action.createReporter.start | requestBody : {}", reporterCUDto);
        ReporterEntity reporterEntity = reporterMapper.cuDtoToEntity(reporterCUDto);
        reporterRepository.save(reporterEntity);
        log.info("Action.createReporter.end | requestBody : {}", reporterCUDto);
    }

    public void updateReporter(ReporterCUDto reporterCUDto, String reporterId) {
        log.info("Action.updateReporter.start id: {} | requestBody : {}", reporterId, reporterCUDto);
        ReporterEntity reporterEntity = findReporterById(reporterId);

        ReporterEntity updatedReporter = reporterMapper.cuDtoToEntity(reporterCUDto);

        updatedReporter.setId(reporterEntity.getId());
        updatedReporter.setTask(reporterEntity.getTask());

        reporterRepository.save(updatedReporter);
        log.info("Action.updateReporter.end id: {} | requestBody : {}", reporterId, reporterCUDto);
    }

    public void deleteReporter(String reporterId){
        reporterRepository.deleteById(reporterId);
    }
}
