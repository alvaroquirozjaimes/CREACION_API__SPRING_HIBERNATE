package org.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

// Marca esta clase como una entidad JPA que se corresponde con una tabla en la base de datos.
@Entity
@Table(name="role") // Especifica el nombre de la tabla en la base de datos.
@ToString // Genera automáticamente el método toString() para esta clase.
@EqualsAndHashCode // Genera automáticamente los métodos equals() y hashCode() para esta clase.
@NoArgsConstructor // Genera un constructor sin argumentos.
@AllArgsConstructor // Genera un constructor con todos los argumentos.
public class Role extends BaseEntity { // Extiende de BaseEntity para heredar sus propiedades.

    @Column(name = "nombre") // Especifica que este campo se mapea a la columna "nombre" en la tabla "role".
    @Getter // Genera automáticamente el método getter para el campo.
    @Setter // Genera automáticamente el método setter para el campo.
    private String nombre; // Almacena el nombre del rol.

    // Configura la relación One-to-Many con la entidad Permiso.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "role")
    @Getter @Setter // Genera automáticamente los métodos getter y setter para el campo.
    private Set<Permiso> permisos; // Almacena un conjunto de permisos asociados con el rol.
}
