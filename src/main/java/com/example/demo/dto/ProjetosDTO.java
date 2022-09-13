package com.example.demo.dto;

import com.example.demo.entities.Projetos;

public class ProjetosDTO {
    private Long id;
    private String projectName;
    private String projectDescription;

    public ProjetosDTO() {
    }

    public ProjetosDTO(final Projetos projeto) {
        super();
        this.id = projeto.getId();
        this.projectName = projeto.getProjectName();
        this.projectDescription = projeto.getProjectDescription();
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
}
