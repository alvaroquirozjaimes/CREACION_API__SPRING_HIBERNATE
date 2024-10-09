package org.example.services;

import org.example.dao.PermisoDao;
import org.example.models.Permiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Marca esta clase como un servicio de Spring que contendrá la lógica de negocio relacionada con permisos.
@Service
public class PermisoService {

    @Autowired // Inyecta automáticamente la instancia de PermisoDao, que proporciona acceso a los datos de permisos.
    PermisoDao permisoDao;

    // Método para obtener todos los permisos.
    public List<Permiso> getAll() {
        // Llama al método getAll() del permisoDao para obtener la lista de permisos.
        return permisoDao.getAll();
    }

    // Método para obtener un permiso específico por su ID.
    public Permiso get(long id) {
        // Llama al método get() del permisoDao para obtener el permiso correspondiente al ID.
        return permisoDao.get(id);
    }

    // Método para registrar un nuevo permiso.
    public Permiso register(Permiso permiso) {
        // Llama al método register() del permisoDao para guardar el nuevo permiso.
        return permisoDao.register(permiso);
    }

    // Método para actualizar un permiso existente.
    public Permiso update(Permiso permiso) {
        // Llama al método update() del permisoDao para actualizar el permiso existente.
        return permisoDao.update(permiso);
    }

    // Método para eliminar un permiso por su ID.
    public void delete(long id) {
        // Llama al método delete() del permisoDao para eliminar el permiso correspondiente al ID.
        permisoDao.delete(id);
    }
}
