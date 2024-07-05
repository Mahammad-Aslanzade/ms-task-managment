package com.example.msjira.dao.repository;

import com.example.msjira.dao.entity.ReporterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReporterRepository extends JpaRepository<ReporterEntity , String> {
}
