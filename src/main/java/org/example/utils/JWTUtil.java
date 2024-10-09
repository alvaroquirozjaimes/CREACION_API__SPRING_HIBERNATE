package org.example.utils;

import io.jsonwebtoken.Claims; // Importa la clase Claims para manejar las reclamaciones del JWT.
import io.jsonwebtoken.JwtBuilder; // Importa JwtBuilder para construir el token JWT.
import io.jsonwebtoken.Jwts; // Importa Jwts para manipular tokens JWT.
import io.jsonwebtoken.SignatureAlgorithm; // Importa la clase para especificar el algoritmo de firma.
import org.slf4j.Logger; // Importa Logger para registrar mensajes de log.
import org.slf4j.LoggerFactory; // Importa LoggerFactory para crear instancias de Logger.
import org.springframework.beans.factory.annotation.Value; // Importa para inyectar propiedades.
import org.springframework.stereotype.Component; // Marca la clase como un componente de Spring.

import javax.crypto.spec.SecretKeySpec; // Importa SecretKeySpec para crear claves secretas.
import java.security.Key; // Importa la clase Key para la firma.
import java.util.Base64; // Importa Base64 para codificar y decodificar claves.
import java.util.Date; // Importa la clase Date para manejar fechas.

@Component // Marca esta clase como un componente de Spring, permitiendo su inyección en otros componentes.
public class JWTUtil {

    @Value("${security.jwt.secret}") // Inyecta el secreto del JWT desde el archivo de propiedades.
    private String key;

    @Value("${security.jwt.issuer}") // Inyecta el emisor del JWT desde el archivo de propiedades.
    private String issuer;

    @Value("${security.jwt.ttlMillis}") // Inyecta el tiempo de vida (TTL) del JWT en milisegundos desde el archivo de propiedades.
    private long ttlMillis;

    private final Logger log = LoggerFactory.getLogger(JWTUtil.class); // Crea un logger para esta clase.

    /**
     * Create a new token.
     *
     * @param id      the ID of the user
     * @param subject the subject (usually the username or email)
     * @return the generated JWT
     */
    public String create(String id, String subject) {
        // Define el algoritmo de firma a utilizar para el JWT.
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis(); // Obtiene la hora actual en milisegundos.
        Date now = new Date(nowMillis); // Crea un objeto Date con la hora actual.

        // Decodifica la clave secreta desde Base64 y crea una clave para firmar el JWT.
        byte[] apiKeySecretBytes = Base64.getDecoder().decode(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Crea un constructor para el JWT.
        JwtBuilder builder = Jwts.builder()
                .setId(id) // Establece el ID del JWT.
                .setIssuedAt(now) // Establece la fecha de emisión.
                .setSubject(subject) // Establece el sujeto del JWT.
                .setIssuer(issuer) // Establece el emisor del JWT.
                .signWith(signatureAlgorithm, signingKey); // Firma el JWT con el algoritmo y la clave.

        // Establece la fecha de expiración del JWT si el TTL es mayor o igual a cero.
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis; // Calcula el tiempo de expiración.
            Date exp = new Date(expMillis); // Crea un objeto Date con la fecha de expiración.
            builder.setExpiration(exp); // Establece la fecha de expiración en el JWT.
        }

        // Construye el JWT y lo serializa en una cadena compacta y segura para URL.
        return builder.compact();
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt the JWT to be validated
     * @return the subject of the JWT
     */
    public String getValue(String jwt) {
        try {
            // Esta línea lanzará una excepción si no es un JWS firmado (como se esperaba).
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.getDecoder().decode(key)) // Establece la clave de firma usando la clave decodificada.
                    .parseClaimsJws(jwt) // Analiza el JWT.
                    .getBody(); // Obtiene las reclamaciones del JWT.

            return claims.getSubject(); // Devuelve el sujeto del JWT.
        } catch (Exception e) {
            log.error("Error parsing JWT: ", e); // Registra el error en caso de excepción.
            return null; // Retorna null o puede lanzar una excepción personalizada.
        }
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt the JWT to be validated
     * @return the ID of the JWT
     */
    public String getKey(String jwt) {
        try {
            // Esta línea lanzará una excepción si no es un JWS firmado (como se esperaba).
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.getDecoder().decode(key)) // Establece la clave de firma usando la clave decodificada.
                    .parseClaimsJws(jwt) // Analiza el JWT.
                    .getBody(); // Obtiene las reclamaciones del JWT.

            return claims.getId(); // Devuelve el ID del JWT.
        } catch (Exception e) {
            log.error("Error parsing JWT: ", e); // Registra el error en caso de excepción.
            return null; // Retorna null o puede lanzar una excepción personalizada.
        }
    }
}
