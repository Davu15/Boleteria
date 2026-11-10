package controlador;

import dao.UsuarioDAO;
import java.sql.SQLException;
import modelo.Sesion;
import modelo.Usuario;

/** Lógica del inicio de sesión. */
public class ControladorLogin {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    /** Si los datos son correctos guarda la sesión y devuelve true. */
    public boolean ingresar(String usuario, String clave) throws SQLException {
        Usuario u = usuarioDAO.autenticar(usuario.trim(), clave);
        if (u == null) {
            return false;
        }
        Sesion.actual = u;
        return true;
    }

    public void cerrarSesion() {
        Sesion.actual = null;
    }
}
