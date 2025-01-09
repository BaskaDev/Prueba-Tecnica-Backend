package com.prueba.backend.controller;


import com.prueba.backend.entity.Project;
import com.prueba.backend.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @Operation(summary = "Obtener todos los proyectos", description = "Devuelve una lista de todos los proyectos registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyectos encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron proyectos")
    })
    @GetMapping
    public List<Project> getAll(){
        return projectService.getAll();
    }

    @Operation(summary = "Crear un nuevo proyecto", description = "Crea un nuevo proyecto con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proyecto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project){

        try {
            Project projectCreated = projectService.createProject(project);
            return  ResponseEntity.status(HttpStatus.CREATED).body(projectCreated);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @Operation(summary = "Actualizar proyecto", description = "Actualiza los datos de un proyecto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer id , @RequestBody Project project){
        try {
            Project projectUpdated = projectService.updateProject(id,project);
            return  ResponseEntity.status(HttpStatus.CREATED).body(projectUpdated);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Eliminar proyecto", description = "Elimina un proyecto por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proyecto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable Integer id) {
        try {
            Project projectDeleted = projectService.deleteProject(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ConfigDataResourceNotFoundException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Buscar proyecto por ID", description = "Devuelve un proyecto por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto encontrado"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @GetMapping("/{id}")
    public Project findProjectById(@PathVariable Integer id){
        return  projectService.findById(id);
    }

}
