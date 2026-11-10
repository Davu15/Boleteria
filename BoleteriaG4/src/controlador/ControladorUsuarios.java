package controlador;

import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Usuario;

/** Lógica de la ventana de usuarios (solo para el administrador). */
public class ControladorUsuarios {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public ArrayList<Usuario> listar() throws SQLException {
        return usuarioDAO.listar();
    }

    public void agregar(Usuario u) throws SQLException {
        usuarioDAO.insertar(u);
    }

    public void modificar(Usuario u) throws SQLException {
        usuarioDAO.actualizar(u);
    }

    public void eliminar(int idUsuario) throws SQLException {
        usuarioDAO.eliminar(idUsuario);
    }
}
