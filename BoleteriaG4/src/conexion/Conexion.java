package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Se encarga SOLO de abrir la conexión con MySQL.
 * Si tu usuario o clave son distintos, cámbialos aquí.
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/boleteria?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de MySQL (carpeta lib).");
        }
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
