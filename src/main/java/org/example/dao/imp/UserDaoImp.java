package org.example.dao.imp;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.dao.UserDao;
import org.example.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional // Indica que los métodos de esta clase se ejecutarán dentro de una transacción.
@Repository // Marca esta clase como un componente de acceso a datos (DAO) que Spring manejará como un bean.
public class UserDaoImp implements UserDao { // Implementa la interfaz UserDao, definiendo los métodos CRUD y el de login.

    @PersistenceContext // Indica que el EntityManager debe ser inyectado por Spring para interactuar con la base de datos.
    EntityManager entityManager;

    @Transactional
    @Override
    public List<User> getAll() {
        // HQL (Hibernate Query Language) para obtener todos los usuarios desde la base de datos.
        String hql = "FROM User as u";
        // Ejecuta la consulta y devuelve una lista de objetos User.
        return (List<User>) entityManager.createQuery(hql).getResultList();
    }

    @Transactional
    @Override
    public User get(long id) {
        // Busca un usuario en la base de datos por su ID y lo devuelve.
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public User register(User user) {
        // Inserta o actualiza un usuario en la base de datos usando merge().
        entityManager.merge(user);
        return user; // Devuelve el usuario registrado o actualizado.
    }

    @Transactional
    @Override
    public User update(User user) {
        // Actualiza los datos de un usuario existente en la base de datos.
        entityManager.merge(user);
        return user; // Devuelve el usuario actualizado.
    }

    @Transactional
    @Override
    public void delete(long id) {
        // Busca al usuario por su ID.
        User user = get(id);
        // Si lo encuentra, lo elimina de la base de datos.
        entityManager.remove(user);
    }

    @Override
    public User login(User dto) {
        boolean isAuthenticated = false; // Variable para indicar si la autenticación fue exitosa o no.

        // HQL para buscar un usuario con email coincidente y contraseña no nula.
        String hql = "FROM User as u WHERE u.password is not null and u.email = :email";

        // Ejecuta la consulta y asigna los resultados a una lista.
        List<User> result = entityManager.createQuery(hql.toString())
                .setParameter("email", dto.getEmail()) // Asigna el valor del email del DTO.
                .getResultList();

        // Si no encuentra ningún usuario con ese email, devuelve null.
        if (result.size() == 0) {
            return null;
        }

        User user = result.get(0); // Obtiene el primer resultado de la lista.
        isAuthenticated = true; // Marca que el usuario ha sido encontrado.

        // Verifica si la contraseña no es vacía.
        if (!StringUtils.isEmpty(dto.getPassword())) {
            // Crea una instancia de Argon2 para verificar la contraseña.
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            // Compara la contraseña almacenada en la base de datos con la proporcionada por el usuario.
            isAuthenticated = argon2.verify(user.getPassword(), dto.getPassword());
        }

        // Si la autenticación es exitosa, devuelve el usuario.
        if (isAuthenticated) {
            return user;
        }

        // Si la autenticación falla, devuelve null.
        return null;
    }
}
