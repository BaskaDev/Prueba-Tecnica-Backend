package com.prueba.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "projects")

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id_project;
    private String name_project;
    @ManyToOne
    @JoinColumn(name = "fk_id_company")
    private Company company;

    public Integer getId_project() {
        return id_project;
    }

    public void setId_project(Integer id_project) {
        this.id_project = id_project;
    }

    public String getName_project() {
        return name_project;
    }

    public void setName_project(String name_project) {
        this.name_project = name_project;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
