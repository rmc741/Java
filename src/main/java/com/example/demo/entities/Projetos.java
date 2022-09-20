package com.example.demo.entities;

import javax.persistence.*;

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

    public Projetos() {
    }

    public Projetos(String projectName, String projectDescription , boolean finished) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.finished = finished;
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
