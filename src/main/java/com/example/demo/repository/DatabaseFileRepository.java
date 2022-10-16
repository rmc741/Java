package com.example.demo.repository;

import com.example.demo.entities.DatabaseFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, Long> {
}
