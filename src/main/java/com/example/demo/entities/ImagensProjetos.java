package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "projects_image")
public class ImagensProjetos {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imageName;
    private String url;

    @ManyToOne
    @JoinColumn(name = "projects", referencedColumnName = "id")
    private Projetos projeto;

    public ImagensProjetos() {
    }

    public ImagensProjetos(String imageName, String url, Projetos projeto) {
        super();
        this.imageName = imageName;
        this.url = url;
        this.projeto = projeto;
    }

    public Long getId() {
        return id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Projetos getProjeto() {
        return projeto;
    }

    public void setProjeto(Projetos projeto) {
        this.projeto = projeto;
    }
}
