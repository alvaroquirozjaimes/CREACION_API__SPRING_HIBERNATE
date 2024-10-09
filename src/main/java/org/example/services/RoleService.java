package org.example.services;

import org.example.dao.RoleDao;
import org.example.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Marca esta clase como un servicio de Spring que contendrá la lógica de negocio relacionada con roles.
@Service
public class RoleService {

    @Autowired // Inyecta automáticamente la instancia de RoleDao para realizar operaciones sobre los datos de roles.
    RoleDao roleDao;

    // Método para obtener todos los roles.
    public List<Role> getAll() {
        // Llama al método getAll() del roleDao para obtener la lista de roles.
        return roleDao.getAll();
    }

    // Método para obtener un rol específico por su ID.
    public Role get(long id) {
        // Llama al método get(id) del roleDao para obtener el rol correspondiente al ID.
        return roleDao.get(id);
    }

    // Método para registrar un nuevo rol.
    public Role register(Role role) {
        // Llama al método register(role) del roleDao para guardar el nuevo rol.
        return roleDao.register(role);
    }

    // Método para actualizar un rol existente.
    public Role update(Role role) {
        // Llama al método update(role) del roleDao para actualizar el rol existente.
        return roleDao.update(role);
    }

    // Método para eliminar un rol por su ID.
    public void delete(long id) {
        // Llama al método delete(id) del roleDao para eliminar el rol correspondiente al ID.
        roleDao.delete(id);
    }
}
