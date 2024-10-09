package org.example.services;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.example.dao.UserDao;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Marca esta clase como un servicio de Spring que contendrá la lógica de negocio relacionada con usuarios.
@Service
public class UserService {

    @Autowired // Inyecta automáticamente la instancia de UserDao para realizar operaciones sobre los datos de usuarios.
    UserDao userDao;

    // Método para obtener todos los usuarios.
    public List<User> getAll() {
        // Llama al método getAll() del userDao para obtener la lista de usuarios.
        return userDao.getAll();
    }

    // Método para obtener un usuario específico por su ID.
    public User get(long id) {
        // Llama al método get(id) del userDao para obtener el usuario correspondiente al ID.
        return userDao.get(id);
    }

    // Método para registrar un nuevo usuario.
    public void register(User user) {
        // Genera un hash de la contraseña del usuario.
        String hash = generarHash(user.getPassword());
        // Establece el hash como la nueva contraseña del usuario.
        user.setPassword(hash);
        // Llama al método register(user) del userDao para guardar el nuevo usuario.
        userDao.register(user);
    }

    // Método para actualizar un usuario existente.
    public User update(User user) {
        // Llama al método update(user) del userDao para actualizar el usuario existente.
        return userDao.update(user);
    }

    // Método para eliminar un usuario por su ID.
    public void delete(long id) {
        // Llama al método delete(id) del userDao para eliminar el usuario correspondiente al ID.
        userDao.delete(id);
    }

    // Método privado para generar un hash a partir de una contraseña.
    private String generarHash(String password) {
        // Crea una instancia de Argon2 para la generación del hash.
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // Genera un hash de la contraseña utilizando Argon2.
        return argon2.hash(1, 1024 * 1, 1, password); // Parámetros: tiempo de ejecución, memoria, hilos, y contraseña.
    }

    // Método para autenticar a un usuario.
    public User login(User user) {
        // Llama al método login(user) del userDao para autenticar al usuario.
        return userDao.login(user);
    }
}
