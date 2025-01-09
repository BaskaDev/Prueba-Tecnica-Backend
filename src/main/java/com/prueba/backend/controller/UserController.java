package com.prueba.backend.controller;


import com.prueba.backend.entity.User;
import com.prueba.backend.entity.UserLogin;
import com.prueba.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista con todos los usuarios registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios")
    })
    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @Operation(summary = "Buscar usuario por ID", description = "Busca un usuario usando su ID único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public User findUserById(@PathVariable Integer id){
        return userService.findById(id);
    }


    @Operation(summary = "Buscar usuario por nombre de usuario", description = "Busca un usuario usando su nombre de usuario único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/find/{username}")
    public User findUserByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user){
        return userService.updateUser(id,user);
    }

    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario con la información proporcionada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public User createUser(@RequestBody User user){
        String encryptedPassword = passwordEncoder.encode(user.getPassword_user());
        user.setPassword_user(encryptedPassword);
        return userService.createUser(user);
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión ", description = "Iniciar sesión ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
            @ApiResponse(responseCode = "400", description = "Faltan credenciales"),
            @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    })
    public ResponseEntity<String> login(@RequestBody UserLogin loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty() ||
                loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("El usuario la contraseña no pueden estar vacíos");
        }


        User user = userService.findByUsername(loginRequest.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario no fue encontrado");
        }


        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword_user())) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario y/o contraseña incorrectos");
        }
    }
}
