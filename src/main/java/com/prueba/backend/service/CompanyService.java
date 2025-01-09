package com.prueba.backend.service;

import com.prueba.backend.entity.Company;
import com.prueba.backend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Company createCompany(Company company){
        return  companyRepository.save(company);
    }

    public List<Company> getAll(){
        return  companyRepository.findAll();
    }
}
