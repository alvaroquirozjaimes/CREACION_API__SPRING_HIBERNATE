package org.example.dao;

import org.example.models.Permiso;

import java.util.List;

public interface PermisoDao {
    // Método para obtener una lista de todos los permisos desde la base de datos.
    List<Permiso> getAll();

    // Método para obtener un permiso específico por su ID.
    Permiso get(long id);

    // Método para registrar un nuevo permiso o actualizar uno existente.
    Permiso register(Permiso permiso);

    // Método para actualizar un permiso existente en la base de datos.
    Permiso update(Permiso permiso);

    // Método para eliminar un permiso de la base de datos por su ID.
    void delete(long id);
}
