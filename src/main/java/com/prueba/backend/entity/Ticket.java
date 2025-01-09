package com.prueba.backend.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tickets;
    @Column
    private String title_tickets;
    @Column
    private String description_tickets;
    @Column
    private String status_tickets;
    @ManyToOne
    @JoinColumn(name = "fk_id_users_story")
    private UserStory userStory;

    public Integer getId_tickets() {
        return id_tickets;
    }

    public void setId_tickets(Integer id_tickets) {
        this.id_tickets = id_tickets;
    }

    public String getTitle_tickets() {
        return title_tickets;
    }

    public void setTitle_tickets(String title_tickets) {
        this.title_tickets = title_tickets;
    }

    public String getStatus_tickets() {
        return status_tickets;
    }

    public void setStatus_tickets(String status_tickets) {
        this.status_tickets = status_tickets;
    }

    public UserStory getUserStory() {
        return userStory;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }

    public String getDescription_tickets() {
        return description_tickets;
    }

    public void setDescription_tickets(String description_tickets) {
        this.description_tickets = description_tickets;
    }
}
