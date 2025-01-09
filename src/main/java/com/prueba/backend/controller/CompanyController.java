package com.prueba.backend.controller;


import com.prueba.backend.entity.Company;
import com.prueba.backend.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Operation(summary = "Crear empresa", description = "Crea una nueva empresa con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa creada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping

    public ResponseEntity<Company> createCompany(@RequestBody Company company){

        try {
            Company companyCreated = companyService.createCompany(company);
            return  ResponseEntity.status(HttpStatus.CREATED).body(companyCreated);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @Operation(summary = "Obtener todas las empresas", description = "Devuelve una lista de todas las empresas registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresas encontradas"),
            @ApiResponse(responseCode = "404", description = "No se encontraron empresas")
    })

    @GetMapping
    public List<Company> getAll(){
        return companyService.getAll();
    }




}
