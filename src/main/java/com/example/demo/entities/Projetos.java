package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
public class Projetos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String projectName;
    @Column(name = "descricao")
    private String projectDescription;
    @Column(name = "finalizado")
    private boolean finished;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projetos")
    private List<DatabaseFile> projectImage;


    public Projetos() {
    }

    public Projetos(String projectName, String projectDescription , boolean finished) {
        super();
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.finished = finished;
    }

    public Projetos(String projectName, String projectDescription, boolean finished, List<DatabaseFile> projectImage) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.finished = finished;
        this.projectImage = projectImage;
    }

    public Long getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<DatabaseFile> getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(List<DatabaseFile> projectImage) {
        this.projectImage = projectImage;
    }

    @Override
    public String toString() {
        return "Projetos{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", finished=" + finished +
                '}';
    }
}
