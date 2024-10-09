package org.example.dao;

import org.example.models.User;

import java.util.List;

public interface UserDao {
    // Método para obtener una lista de todos los usuarios desde la base de datos.
    List<User> getAll();

    // Método para obtener un usuario específico por su ID.
    User get(long id);

    // Método para registrar un nuevo usuario o actualizar uno existente.
    User register(User user);

    // Método para actualizar un usuario existente en la base de datos.
    User update(User user);

    // Método para eliminar un usuario de la base de datos por su ID.
    void delete(long id);

    // Método para autenticar a un usuario, verificando las credenciales proporcionadas.
    User login(User user);
}
