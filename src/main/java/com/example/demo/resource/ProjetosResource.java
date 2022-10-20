package com.example.demo.resource;

import com.example.demo.dto.ProjetosDTO;
import com.example.demo.entities.DatabaseFile;
import com.example.demo.entities.Projetos;
import com.example.demo.services.DatabaseFileService;
import com.example.demo.services.ProjetosService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/projetos")
public class ProjetosResource {

    private final ProjetosService projetosService;

    private final DatabaseFileService databaseFileService;

    public ProjetosResource(ProjetosService projetosService, DatabaseFileService databaseFileService) {
        this.projetosService = projetosService;
        this.databaseFileService = databaseFileService;
    }


    @GetMapping("/fotos/{projectId}")
    public ResponseEntity<ByteArrayResource> pegaImagens(@PathVariable final Long projectId , HttpServletRequest request){
        // Load file as Resource
        /*DatabaseFile databaseFile = fileStorageService.getFileById(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFile.getData()));*/
        //List<DatabaseFile> files = databaseFileService.getAllByProjectId(projectId);
        DatabaseFile files =  projetosService.getAllImagensByProjectId(projectId);

        ByteArrayResource projectImages = new ByteArrayResource(files.getData());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION , "attachment; filename=\"" + files.getFileName() + "\"")
                .body(projectImages);
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
