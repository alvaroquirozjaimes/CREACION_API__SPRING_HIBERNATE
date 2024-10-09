package org.example.controllers;

import org.example.models.Permiso;
import org.example.services.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase es un controlador de Spring que manejará las solicitudes HTTP y devolverá datos en formato JSON.
@RequestMapping("permiso") // Define la ruta base para este controlador, en este caso "permiso". Todas las solicitudes a rutas como "/permiso" se manejarán aquí.
public class PermisoController {

    @Autowired // Inyecta automáticamente la instancia de PermisoService en este controlador para que podamos usar sus métodos.
    PermisoService permisoService;

    // Método para obtener todos los permisos
    @RequestMapping(value = "/", method = RequestMethod.GET) // Define la ruta y el método HTTP. Aquí se usa GET en la ruta "/permiso/".
    List<Permiso> getAll() {
        // Llama al servicio para obtener una lista de todos los permisos
        return permisoService.getAll();
    }

    // Método para obtener un permiso por su ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // La ruta incluye un parámetro "id" que se pasa en la URL.
    Permiso get(@PathVariable long id) { // @PathVariable indica que "id" proviene de la URL.
        // Llama al servicio para obtener el permiso con el ID especificado.
        return permisoService.get(id);
    }

    // Método para registrar un nuevo permiso
    @RequestMapping(value = "/", method = RequestMethod.POST) // Define una ruta POST en "/permiso/" para crear un nuevo permiso.
    Permiso register(@RequestBody Permiso permiso) { // @RequestBody toma el cuerpo de la solicitud y lo convierte en un objeto Permiso.
        // Llama al servicio para registrar el nuevo permiso.
        return permisoService.register(permiso);
    }

    // Método para actualizar un permiso existente
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT) // Define una ruta PUT en "/permiso/{id}" para actualizar un permiso.
    Permiso update(@RequestBody Permiso permiso) { // Recibe el objeto Permiso actualizado desde el cuerpo de la solicitud.
        // Llama al servicio para actualizar el permiso.
        return permisoService.update(permiso);
    }

    // Método para eliminar un permiso por su ID
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // Define una ruta DELETE en "/permiso/{id}" para eliminar un permiso.
    void delete(@PathVariable long id) { // Recibe el ID del permiso a eliminar desde la URL.
        // Llama al servicio para eliminar el permiso con el ID especificado.
        permisoService.delete(id);
    }
}
