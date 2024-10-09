package org.example.controllers;

import org.example.models.Role;
import org.example.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase es un controlador REST que gestionará las solicitudes HTTP y devolverá respuestas en formato JSON.
@RequestMapping("role") // Define la ruta base "/role" para este controlador. Todas las solicitudes que comiencen con "/role" se manejarán en esta clase.
public class RoleController {

    @Autowired // Inyección automática del servicio RoleService. Spring se encarga de instanciar RoleService para que podamos usar sus métodos.
    RoleService roleService;

    // Método para obtener todos los roles
    @RequestMapping(value = "/", method = RequestMethod.GET) // Define que este método maneja solicitudes GET en "/role/".
    List<Role> getAll() {
        // Llama al servicio para obtener una lista de todos los roles.
        return roleService.getAll();
    }

    // Método para obtener un rol específico por su ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // Define que este método maneja solicitudes GET en "/role/{id}".
    Role get(@PathVariable long id) { // @PathVariable indica que "id" proviene de la URL.
        // Llama al servicio para obtener el rol correspondiente al ID proporcionado.
        return roleService.get(id);
    }

    // Método para registrar un nuevo rol
    @RequestMapping(value = "/", method = RequestMethod.POST) // Define que este método maneja solicitudes POST en "/role/".
    Role register(@RequestBody Role role) { // @RequestBody toma el cuerpo de la solicitud HTTP y lo convierte en un objeto Role.
        // Llama al servicio para registrar el nuevo rol.
        return roleService.register(role);
    }

    // Método para actualizar un rol existente
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT) // Define que este método maneja solicitudes PUT en "/role/{id}".
    Role update(@RequestBody Role role) { // @RequestBody toma los datos actualizados del rol desde el cuerpo de la solicitud.
        // Llama al servicio para actualizar el rol.
        return roleService.update(role);
    }

    // Método para eliminar un rol por su ID
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // Define que este método maneja solicitudes DELETE en "/role/{id}".
    void delete(@PathVariable long id) { // Recibe el ID del rol a eliminar desde la URL.
        // Llama al servicio para eliminar el rol correspondiente al ID proporcionado.
        roleService.delete(id);
    }
}
