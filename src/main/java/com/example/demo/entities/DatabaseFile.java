package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class DatabaseFile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    @ManyToOne @JsonIgnore
    @JoinColumn(name = "projects", referencedColumnName = "id")
    private Projetos projetos;


    public DatabaseFile() {
    }

    public DatabaseFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public DatabaseFile(String fileName, String fileType, byte[] data , Projetos projetos) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.projetos = projetos;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Projetos getProjetos() {
        return projetos;
    }

    public void setProjetos(Projetos projetos) {
        this.projetos = projetos;
    }
}
