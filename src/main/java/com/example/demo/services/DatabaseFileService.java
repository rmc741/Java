package com.example.demo.services;

import com.example.demo.entities.DatabaseFile;
import com.example.demo.entities.Projetos;
import com.example.demo.exception.FileNotFoundException;
import com.example.demo.exception.FileStorageException;
import com.example.demo.repository.DatabaseFileRepository;
import com.example.demo.repository.ProjetosRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DatabaseFileService {

    private ProjetosRepository projetosRepository;
    private DatabaseFileRepository databaseFileRepository;

    public DatabaseFileService(ProjetosRepository projetosRepository, DatabaseFileRepository databaseFileRepository) {
        this.projetosRepository = projetosRepository;
        this.databaseFileRepository = databaseFileRepository;
    }

    //Metodo para salvar arquivo na base de dados
    public DatabaseFile storeFile(MultipartFile file){
        //Pega o nome do arquivo
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        //Validação se nome possui nome invalido
        try {
            if(fileName.contains("..")){
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            //Salvando arquivo no objeto
            DatabaseFile dbFile = new DatabaseFile(fileName , file.getContentType() , file.getBytes());
            //Salvando arquivo na base de dados
            return databaseFileRepository.save(dbFile);

        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    //Metodo para pegar arquivo na base pelo id
    public DatabaseFile getFileById(Long fileId){
        return databaseFileRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("Não foi possivel encontrar arquivo pelo ID: " + fileId));
    }

    public List<DatabaseFile> getAll(){
        return databaseFileRepository.findAll();
    }

    public List<DatabaseFile> getAllByProjectId(Long projetoId){
        return databaseFileRepository.findByProjectId(projetoId);
    }
}
