package com.example.msjira.controller

import com.example.msjira.dao.entity.ReporterEntity
import com.example.msjira.dao.repository.ReporterRepository
import com.example.msjira.mapper.ReporterMapper
import com.example.msjira.model.reporter.ReporterDto
import com.example.msjira.service.ReporterService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class ReporterControllerTest extends Specification {

    private ReporterRepository reporterRepository
    private ReporterService reporterService
    private ReporterMapper reporterMapper
    private EnhancedRandom enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandom()


    void setup() {
        reporterMapper = Mock()
        reporterRepository = Mock()
        reporterService = new ReporterService(reporterRepository, reporterMapper)
    }

    def "GetReporterById"() {

        given :

        def reporterId = "9412mka-fasfka"
        def reporterEntity = enhancedRandom.nextObject(ReporterEntity)
        def reporterDto  = enhancedRandom.nextObject(ReporterDto)

        when :

        def result = reporterService.getReporterById(reporterId)

        then :

        1 * reporterRepository.findById(reporterId) >> Optional.of(reporterEntity)
        1 * reporterMapper.mapToDto(reporterEntity) >> reporterDto

        result == reporterDto

    }
}
