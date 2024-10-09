package org.example.controllers;

import org.example.models.User;
import org.example.services.UserService;
import org.example.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // Indica que esta clase es un controlador REST, lo que significa que manejará solicitudes HTTP y devolverá respuestas en JSON.
@RequestMapping("user") // Define la ruta base "/user" para este controlador. Todas las solicitudes que comiencen con "/user" se manejarán aquí.
public class UserController {

    @Autowired // Inyecta automáticamente el servicio UserService, que contiene la lógica de negocio para los usuarios.
    UserService userService;

    @Autowired // Inyecta automáticamente el JWTUtil, una clase para manejar la creación de tokens JWT.
    private JWTUtil jwtUtil;

    @GetMapping("/") // Define que este método manejará solicitudes GET en "/user/".
    List<User> getAll() {
        // Llama al servicio para obtener una lista de todos los usuarios.
        return userService.getAll();
    }

    @GetMapping("/{id}") // Define que este método manejará solicitudes GET en "/user/{id}".
    User get(@PathVariable long id) { // @PathVariable toma el parámetro "id" de la URL.
        // Llama al servicio para obtener un usuario específico por su ID.
        return userService.get(id);
    }

    @PostMapping("/") // Define que este método manejará solicitudes POST en "/user/" para registrar un nuevo usuario.
    void register(@RequestBody User user) { // @RequestBody toma el cuerpo de la solicitud HTTP y lo convierte en un objeto User.
        // Llama al servicio para registrar un nuevo usuario.
        userService.register(user);
    }

    @PutMapping("/{id}") // Define que este método manejará solicitudes PUT en "/user/{id}" para actualizar un usuario existente.
    User update(@PathVariable long id, @RequestBody User user) { // @PathVariable toma el "id" de la URL y @RequestBody toma los datos actualizados del usuario.
        // Establece el ID del usuario en el objeto antes de actualizarlo.
        user.setId(id);
        // Llama al servicio para actualizar el usuario.
        return userService.update(user);
    }

    @DeleteMapping("/{id}") // Define que este método manejará solicitudes DELETE en "/user/{id}" para eliminar un usuario.
    void delete(@PathVariable long id) { // Recibe el ID del usuario a eliminar desde la URL.
        // Llama al servicio para eliminar el usuario con el ID especificado.
        userService.delete(id);
    }

    @PostMapping("/login") // Define que este método manejará solicitudes POST en "/user/login" para iniciar sesión.
    Map<String, Object> login(@RequestBody User dto) { // @RequestBody toma los datos del usuario (email y password) desde la solicitud.
        // Llama al servicio para autenticar al usuario.
        User user = userService.login(dto);

        // Crea un mapa para almacenar el resultado, que incluirá el token y el usuario si la autenticación es exitosa.
        Map<String, Object> result = new HashMap<>();
        if (user != null) {
            // Si el usuario es válido, crea un token JWT con el ID y email del usuario.
            String token = jwtUtil.create(String.valueOf(user.getId()), user.getEmail());
            // Almacena el token y los datos del usuario en el resultado.
            result.put("token", token);
            result.put("user", user);
        }
        // Devuelve el resultado (mapa con token y usuario o vacío si falló la autenticación).
        return result;
    }
}
