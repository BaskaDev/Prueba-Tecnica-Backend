package com.prueba.backend.entity;

import jakarta.persistence.*;


@Entity
@Table(name="users_stories")
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_users_stories;

    private String title_users_stories;
    @ManyToOne
    @JoinColumn(name = "fk_id_project")
    private Project project;

    public Integer getId_users_stories() {
        return id_users_stories;
    }

    public void setId_users_stories(Integer id_users_stories) {
        this.id_users_stories = id_users_stories;
    }

    public String getTitle_users_stories() {
        return title_users_stories;
    }

    public void setTitle_users_stories(String title_users_stories) {
        this.title_users_stories = title_users_stories;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
