package com.example.msjira.dao.repository;

import com.example.msjira.dao.entity.TeleSalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeleSalesRepository extends JpaRepository<TeleSalesEntity, String> {

    @Query("SELECT ts FROM TeleSalesEntity ts LEFT JOIN FETCH ts.tasks t GROUP BY ts.id ORDER BY COUNT(t) ASC")
    Optional<TeleSalesEntity> findTeleSalesWithFewestTasks();

    @Query(value = "SELECT ts.* FROM tele_sales ts " +
            "LEFT JOIN ( " +
            "    SELECT asignee_id, COUNT(*) AS tasks_count " +
            "    FROM tasks " +
            "    WHERE status <> 'DONE' " +
            "    GROUP BY asignee_id " +
            ") AS tb ON ts.id = tb.asignee_id " +
            "ORDER BY COALESCE(tb.tasks_count, 0) ASC " +
            "LIMIT 1",
            nativeQuery = true)
    Optional<TeleSalesEntity> findTeleSalesWithFewestTasksExcludingDone();

    @Query(value = "SELECT COUNT(*) > 0 FROM tele_sales ts JOIN tasks t ON ts.id = t.asignee_id WHERE ts.id = :teleSaleId AND t.id = :taskId", nativeQuery = true)
    boolean existsTaskForTeleSale(@Param("teleSaleId") String teleSaleId, @Param("taskId") String taskId);
}
