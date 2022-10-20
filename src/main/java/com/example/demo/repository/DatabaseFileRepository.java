package com.example.demo.repository;

import com.example.demo.entities.DatabaseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, Long> {

    @Query(
            value = "SELECT * FROM files WHERE projects = ?1",
            nativeQuery = true)
    DatabaseFile findByProjectId(@Param("projects")final Long projectId);
}
