package org.example.models;

import jakarta.persistence.*;

import java.util.Date;

// Indica que esta clase es una superclase que se puede mapear a otras entidades.
// Las entidades que hereden de esta clase obtendrán sus atributos.
@MappedSuperclass
public class BaseEntity {
    // Define un identificador único para la entidad.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La estrategia de generación de ID es autoincremental en la base de datos.
    @Column(name = "id", updatable = false, nullable = false) // La columna "id" no se puede actualizar y no puede ser nula.
    private long id;

    // Fecha de actualización de la entidad.
    @Column(columnDefinition = "DATETIME", updatable = false, nullable = false) // Define la columna como DATETIME, no actualizable y no puede ser nula.
    protected Date updateDate;

    // Fecha de creación de la entidad.
    @Column(columnDefinition = "DATETIME", updatable = false, nullable = false) // Igual que updateDate, no actualizable y no puede ser nula.
    protected Date createDate;

    // Método para obtener el ID de la entidad.
    public long getId() {
        return id;
    }

    // Método para establecer el ID de la entidad.
    public void setId(long id) {
        this.id = id;
    }

    // Método que se ejecuta antes de persistir la entidad en la base de datos.
    @PrePersist
    protected void onCreate() {
        updateDate = new Date(); // Establece la fecha de actualización al momento actual.
        if (createDate == null) { // Si la fecha de creación es nula, se establece también al momento actual.
            createDate = new Date();
        }
    }
}
