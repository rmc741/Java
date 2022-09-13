package com.example.demo.resource;

import com.example.demo.dto.ProjetosDTO;
import com.example.demo.entities.Projetos;
import com.example.demo.services.ProjetosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/projetos")
public class ProjetosResource {

    private final ProjetosService projetosService;

    public ProjetosResource(ProjetosService projetosService) {
        this.projetosService = projetosService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjetosDTO>> findAll(){
        List<ProjetosDTO> projetosDTOList = projetosService.buscarTodos().stream()
                .map(obj -> new ProjetosDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(projetosDTOList);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjetosDTO> findProject(@PathVariable final Long projectId){
        ProjetosDTO projetosDTO = new ProjetosDTO(projetosService.buscarProjetoId(projectId));

        return ResponseEntity.ok().body(projetosDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<ProjetosDTO> createProject(@Valid @RequestBody final ProjetosDTO projetosDTO){
        Projetos projeto = projetosService.adicionarProjeto(projetosDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{projectId}").buildAndExpand(projeto.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/edit/{projectId}")
    public ResponseEntity<ProjetosDTO> editProject(@PathVariable Long projectId , @Valid @RequestBody final ProjetosDTO projetosDTO){
        ProjetosDTO projeto = new ProjetosDTO(projetosService.atualizarProjeto(projectId, projetosDTO));
        return ResponseEntity.ok().body(projeto);
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId){
        projetosService.deletarProjeto(projectId);
        return ResponseEntity.noContent().build();
    }
}
