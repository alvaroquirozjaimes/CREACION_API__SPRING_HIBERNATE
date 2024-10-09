package org.example.utils;

import org.springframework.boot.SpringApplication; // Importa la clase SpringApplication para iniciar la aplicación.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa la anotación para configurar automáticamente la aplicación Spring.

@SpringBootApplication // Anotación que indica que esta clase es la configuración principal de la aplicación.
public class Application {
    public static void main(String[] args) {
        // Llama al método run de SpringApplication para iniciar la aplicación.
        SpringApplication.run(Application.class, args);
    }
}
