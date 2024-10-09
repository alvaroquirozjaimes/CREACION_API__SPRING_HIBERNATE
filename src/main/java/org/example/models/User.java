package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

// Marca esta clase como una entidad JPA que se corresponde con una tabla en la base de datos.
@Entity
@Table(name = "user") // Especifica el nombre de la tabla en la base de datos.
@ToString // Genera automáticamente el método toString() para esta clase.
@EqualsAndHashCode // Genera automáticamente los métodos equals() y hashCode() para esta clase.
@AllArgsConstructor // Genera un constructor con todos los argumentos.
public class User extends BaseEntity { // Extiende de BaseEntity para heredar sus propiedades.

    // Relación Many-to-One con la entidad Role.
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Indica que este campo es de solo lectura en JSON.
    @ManyToOne(fetch = FetchType.EAGER) // Define la relación de muchos a uno con Role, cargando de forma eager.
    @JoinColumn(name = "role_id") // Especifica la columna "role_id" como la clave foránea en la tabla "user".
    @Getter // Genera automáticamente el método getter para el campo.
    @Setter // Genera automáticamente el método setter para el campo.
    private Role role; // Almacena el rol asociado con el usuario.

    // Campo para la contraseña del usuario.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Indica que este campo es de solo escritura en JSON.
    @Column(name = "password") // Especifica que este campo se mapea a la columna "password" en la tabla "user".
    @Getter @Setter // Genera automáticamente los métodos getter y setter para el campo.
    private String password; // Almacena la contraseña del usuario.

    // Campos para almacenar información personal del usuario.
    @Column(name = "nombre") // Mapea este campo a la columna "nombre" de la tabla "user".
    @Getter @Setter // Genera automáticamente los métodos getter y setter para el campo.
    private String nombre; // Almacena el nombre del usuario.

    @Column(name = "apellido") // Mapea este campo a la columna "apellido" de la tabla "user".
    @Getter @Setter // Genera automáticamente los métodos getter y setter para el campo.
    private String apellido; // Almacena el apellido del usuario.

    @Column(name = "email") // Mapea este campo a la columna "email" de la tabla "user".
    @Getter @Setter // Genera automáticamente los métodos getter y setter para el campo.
    private String email; // Almacena el correo electrónico del usuario.

    @Column(name = "telefono") // Mapea este campo a la columna "telefono" de la tabla "user".
    @Getter @Setter // Genera automáticamente los métodos getter y setter para el campo.
    private String telefono; // Almacena el número de teléfono del usuario.

    @Column(name = "fecha_nac") // Mapea este campo a la columna "fecha_nac" de la tabla "user".
    @Getter @Setter // Genera automáticamente los métodos getter y setter para el campo.
    private Date fechaNac; // Almacena la fecha de nacimiento del usuario.

    // Constructor por defecto (sin argumentos).
    public User() {
    }

    // Constructor con parámetros para inicializar los campos comunes del usuario.
    public User(long id, String nombre, String apellido,
                String email, String telefono, Date fechaNac) {
        setId(id); // Establece el ID usando el setter de la clase base.
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
    }
}
