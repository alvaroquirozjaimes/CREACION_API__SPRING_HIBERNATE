package org.example.dao.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.dao.RoleDao;
import org.example.models.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional // Indica que los métodos de esta clase se ejecutan dentro de transacciones, asegurando que las operaciones sobre la base de datos sean atómicas.
@Repository // Marca esta clase como un componente de acceso a datos (DAO), lo que permite que Spring la detecte y la maneje como un bean de persistencia.
public class RoleDaoImp implements RoleDao { // Esta clase implementa la interfaz RoleDao, que define las operaciones CRUD para la entidad Role.

    @PersistenceContext // Indica que el EntityManager debe ser inyectado automáticamente por Spring para interactuar con la base de datos.
    protected EntityManager entityManager;

    @Transactional
    @Override
    public List<Role> getAll() {
        // HQL (Hibernate Query Language) para obtener todos los registros de la entidad Role.
        String hql = "FROM Role as u ";
        // Ejecuta la consulta y devuelve una lista de Roles.
        return (List<Role>) entityManager.createQuery(hql).getResultList();
    }

    @Transactional
    @Override
    public Role get(long id) {
        // Busca un rol en la base de datos usando su ID.
        return entityManager.find(Role.class, id);
    }

    @Transactional
    @Override
    public Role register(Role role) {
        // Inserta o actualiza un rol en la base de datos. Si el rol ya existe, lo actualiza; si no, lo inserta.
        entityManager.merge(role);
        return role; // Devuelve el objeto Role registrado o actualizado.
    }

    @Transactional
    @Override
    public Role update(Role role) {
        // Actualiza el rol en la base de datos. Es similar al método register.
        entityManager.merge(role);
        return role; // Devuelve el rol actualizado.
    }

    @Transactional
    @Override
    public void delete(long id) {
        // Busca el rol por su ID usando el método get().
        Role role = get(id);
        // Elimina el rol encontrado de la base de datos.
        entityManager.remove(role);
    }
}
