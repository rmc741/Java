package com.example.demo.services;

import com.example.demo.dto.ProjetosDTO;
import com.example.demo.entities.DatabaseFile;
import com.example.demo.entities.Projetos;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.repository.DatabaseFileRepository;
import com.example.demo.repository.ProjetosRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetosService {

    private final ProjetosRepository projetosRepository;
    private final DatabaseFileRepository databaseFileRepository;


    public ProjetosService(ProjetosRepository projetosRepository, DatabaseFileRepository databaseFileRepository) {
        this.projetosRepository = projetosRepository;
        this.databaseFileRepository = databaseFileRepository;
    }

    public List<Projetos> buscarTodos(){ return projetosRepository.findAll(); }

    public Projetos buscarProjetoId (Long id){
        Optional<Projetos> projeto = projetosRepository.findById(id);

        return projeto.orElseThrow(() -> new ObjectNotFoundException("Projeto não localizado!!"));
    }

    public Projetos adicionarProjeto(final ProjetosDTO projetosDTO){
        return projetosRepository.save(new Projetos(projetosDTO.getProjectName() , projetosDTO.getProjectDescription() , false));
    }

    public Projetos atualizarProjeto(final Long projetoId , @Valid final ProjetosDTO projetosDTO){
        Projetos projeto = buscarProjetoId(projetoId);

        projeto.setProjectName(projetosDTO.getProjectName());
        projeto.setProjectDescription(projetosDTO.getProjectDescription());
        projeto.setFinished(projetosDTO.getFinished());

        return projetosRepository.save(projeto);
    }

    public void deletarProjeto(final Long projetoId){
        Projetos projeto = buscarProjetoId(projetoId);

        projetosRepository.delete(projeto);
    }

    public DatabaseFile getAllImagensByProjectId(final Long projectId){
        return databaseFileRepository.findByProjectId(projectId);
    }
}
