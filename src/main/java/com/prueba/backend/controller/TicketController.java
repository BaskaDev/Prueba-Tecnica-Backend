package com.prueba.backend.controller;


import com.prueba.backend.entity.Ticket;
import com.prueba.backend.service.TicketService;
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
@RequestMapping("api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Operation(summary = "Obtener todos los tickets", description = "Devuelve una lista de todos los tickets registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron tickets")
    })
    @GetMapping
    public List<Ticket> getAll(){
        return ticketService.getAll();
    }

    @Operation(summary = "Buscar ticket por ID", description = "Devuelve un ticket por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket encontrado"),
            @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    @GetMapping("/{id}")
    public Ticket findTicketById(@PathVariable Integer id){
        return ticketService.findById(id);
    }

    @Operation(summary = "Crear un nuevo ticket", description = "Crea un nuevo ticket con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ticket creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        try{
            Ticket ticketCreated = ticketService.createTicket(ticket);
            return ResponseEntity.status(HttpStatus.CREATED).body(ticketCreated);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Actualizar ticket", description = "Actualiza los datos de un ticket existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    @PutMapping("/{id}")
    ResponseEntity<Ticket> updateTicket(@PathVariable Integer id, @RequestBody Ticket ticket){
        try{
            Ticket ticketUpdated = ticketService.updateTicket(id,ticket);
            return ResponseEntity.status(HttpStatus.CREATED).body(ticketUpdated);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Eliminar ticket", description = "Elimina un ticket por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ticket eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Ticket> deleteTicket (@PathVariable Integer id){
        try{
            Ticket ticketDeleted = ticketService.deleteTicket(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (ConfigDataResourceNotFoundException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
