package org.example.dao.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.dao.PermisoDao;
import org.example.models.Permiso;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional // Indica que todos los métodos dentro de esta clase se ejecutarán en el contexto de una transacción.
@Repository // Marca esta clase como un componente de acceso a datos (DAO). Spring detecta esta clase y la trata como un bean de persistencia.
public class PermisoDaoImp implements PermisoDao { // Esta clase implementa la interfaz PermisoDao, que define las operaciones CRUD.

    @PersistenceContext // Indica a Spring que inyecte automáticamente el EntityManager que maneja la interacción con la base de datos.
    protected EntityManager entityManager;

    @Transactional
    @Override
    public List<Permiso> getAll() {
        // Consulta HQL que obtiene todos los registros de la tabla Permiso.
        String hql = "FROM Permiso as u";
        // Ejecuta la consulta y devuelve el resultado como una lista de Permiso.
        return (List<Permiso>) entityManager.createQuery(hql).getResultList();
    }

    @Transactional
    @Override
    public Permiso get(long id) {
        // Busca un permiso en la base de datos usando su ID.
        return entityManager.find(Permiso.class, id);
    }

    @Transactional
    @Override
    public Permiso register(Permiso permiso) {
        // Inserta o actualiza un permiso en la base de datos. Si el permiso ya existe, se actualiza; si no, se inserta.
        entityManager.merge(permiso);
        // Devuelve el objeto permiso registrado o actualizado.
        return permiso;
    }

    @Transactional
    @Override
    public Permiso update(Permiso permiso) {
        // Actualiza un permiso existente en la base de datos.
        entityManager.merge(permiso);
        return permiso;
    }

    @Transactional
    @Override
    public void delete(long id) {
        // Primero busca el permiso por su ID, usando el método get().
        Permiso permiso = get(id);
        // Luego elimina el permiso encontrado de la base de datos.
        entityManager.remove(permiso);
    }
}
