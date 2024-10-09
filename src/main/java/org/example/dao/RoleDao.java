package org.example.dao;

import org.example.models.Role;

import java.util.List;

public interface RoleDao {
    // Método para obtener una lista de todos los roles desde la base de datos.
    List<Role> getAll();

    // Método para obtener un rol específico por su ID.
    Role get(long id);

    // Método para registrar un nuevo rol o actualizar uno existente.
    Role register(Role role);

    // Método para actualizar un rol existente en la base de datos.
    Role update(Role role);

    // Método para eliminar un rol de la base de datos por su ID.
    void delete(long id);
}
