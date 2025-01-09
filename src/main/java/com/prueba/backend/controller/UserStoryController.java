package com.prueba.backend.controller;


import com.prueba.backend.entity.UserStory;
import com.prueba.backend.service.UserStoryService;
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
@RequestMapping("api/userStory")
public class UserStoryController {

    @Autowired
    private UserStoryService userStoryService;

    @Operation(summary = "Obtener todas las User Stories", description = "Devuelve una lista de todas las User Stories registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Stories encontradas"),
            @ApiResponse(responseCode = "404", description = "No se encontraron User Stories")
    })
    @GetMapping
    public List<UserStory> getAll(){
        return  userStoryService.readAll();
    }

    @Operation(summary = "Crear una nueva User Story", description = "Crea una nueva User Story con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Story creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<UserStory> createUserStory(@RequestBody UserStory userStory){

        try {
            UserStory userStoryCreated = userStoryService.create(userStory);
            return  ResponseEntity.status(HttpStatus.CREATED).body(userStoryCreated);
        }catch(Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @Operation(summary = "Actualizar una User Story", description = "Actualiza los datos de una User Story existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Story actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "User Story no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserStory> updateUserStory(@PathVariable Integer id, @RequestBody UserStory userStory){
        try {
            UserStory userStoryUpdated = userStoryService.updateUserStory(id,userStory);
            return ResponseEntity.status(HttpStatus.CREATED).body(userStoryUpdated);
        }catch(Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Eliminar una User Story", description = "Elimina una User Story por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User Story eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "User Story no encontrada")
    })
    @DeleteMapping("/{id}")
    public  ResponseEntity<UserStory> deleteUserStory(@PathVariable Integer id){
        try{
            UserStory userStoryDeleted = userStoryService.deleteUserStory(id);
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (ConfigDataResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Buscar User Story por ID", description = "Devuelve una User Story por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Story encontrada"),
            @ApiResponse(responseCode = "404", description = "User Story no encontrada")
    })
    @GetMapping("/{id}")
    public UserStory findUserStory(@PathVariable Integer id){
        return  userStoryService.findById(id);
    }
}
