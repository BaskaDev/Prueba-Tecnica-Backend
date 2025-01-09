package com.prueba.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name="COMPANIES")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_company;
    @Column
    private String name_company;
    @Column
    private String nit_company;
    @Column
    private String address_company;
    @Column
    private String email_company;
    @Column
    private String phone_company;


    public Integer getId_company() {
        return id_company;
    }

    public void setId_company(Integer id_company) {
        this.id_company = id_company;
    }

    public String getName_company() {
        return name_company;
    }

    public void setName_company(String name_company) {
        this.name_company = name_company;
    }

    public String getNit_company() {
        return nit_company;
    }

    public void setNit_company(String nit_company) {
        this.nit_company = nit_company;
    }

    public String getAddress_company() {
        return address_company;
    }

    public void setAddress_company(String address_company) {
        this.address_company = address_company;
    }

    public String getEmail_company() {
        return email_company;
    }

    public void setEmail_company(String email_company) {
        this.email_company = email_company;
    }

    public String getPhone_company() {
        return phone_company;
    }

    public void setPhone_company(String phone_company) {
        this.phone_company = phone_company;
    }
}
