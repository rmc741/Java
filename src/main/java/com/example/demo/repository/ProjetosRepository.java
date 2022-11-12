package com.example.demo.repository;

import com.example.demo.entities.Projetos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjetosRepository extends JpaRepository<Projetos , Long> {
    Optional<Projetos> findById(Long id);
    //List<Projetos> findByTitleContaining(String projectName);

    List<Projetos> findByProjectNameContaning(String projectName);

}
