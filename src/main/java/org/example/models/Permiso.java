package org.example.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

// Marca esta clase como una entidad JPA que se corresponde con una tabla en la base de datos.
@Entity
@Table(name = "permisos") // Especifica el nombre de la tabla en la base de datos.
@NoArgsConstructor // Genera un constructor sin argumentos.
@AllArgsConstructor // Genera un constructor con todos los argumentos.
public class Permiso extends BaseEntity { // Extiende de BaseEntity para heredar sus propiedades.

    @Column(name = "nombre") // Especifica que este campo se mapea a la columna "nombre" en la tabla "permisos".
    @Getter // Genera automáticamente el método getter para el campo.
    @Setter // Genera automáticamente el método setter para el campo.
    private String nombre; // Almacena el nombre del permiso.

    // Configura la relación Many-to-One con la entidad Role.
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true) // Mantiene la referencia a Role usando solo su ID.
    @JsonProperty("role_id") // Establece el nombre del campo en el JSON.
    @ManyToOne(fetch = FetchType.LAZY) // Define una relación de muchos a uno, cargando la entidad Role de forma perezosa.
    @JoinColumn(name = "role_id") // Especifica la columna que actúa como la clave foránea en la tabla "permisos".
    @Getter @Setter // Genera automáticamente los métodos getter y setter para el campo.
    private Role role; // Almacena la referencia al rol asociado con el permiso.

    @Override
    public String toString() { // Método que devuelve una representación en cadena del objeto Permiso.
        return "Permiso{" +
                "nombre='" + nombre + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) { // Método que determina si dos objetos Permiso son iguales.
        if (this == o) return true; // Comprueba si son el mismo objeto.
        if (o == null || getClass() != o.getClass()) return false; // Comprueba si son de la misma clase.
        Permiso permiso = (Permiso) o; // Convierte el objeto a Permiso.
        return Objects.equals(nombre, permiso.nombre); // Compara el nombre de ambos permisos.
    }

    @Override
    public int hashCode() { // Método que genera un código hash para el objeto Permiso.
        return Objects.hashCode(nombre); // Devuelve el código hash del nombre.
    }
}
