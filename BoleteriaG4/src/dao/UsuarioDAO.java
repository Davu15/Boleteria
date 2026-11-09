package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Usuario;

/** Acceso a la tabla usuario (solo SQL, nada de pantallas). */
public class UsuarioDAO {

    /** Revisa usuario y clave. Devuelve el Usuario si es correcto, o null si no. */
    public Usuario autenticar(String usuario, String clave) throws SQLException {
        // La clave se compara cifrada: SHA2(clave, 256)
        String sql = "SELECT id_usuario, nombre, usuario, perfil, activo FROM usuario "
                + "WHERE usuario = ? AND clave = SHA2(?, 256) AND activo = 1";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, clave);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return leerFila(rs);
                }
            }
        }
        return null;
    }

    public ArrayList<Usuario> listar() throws SQLException {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre, usuario, perfil, activo FROM usuario ORDER BY nombre";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(leerFila(rs));
            }
        }
        return lista;
    }

    public void insertar(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuario (nombre, usuario, clave, perfil, activo) VALUES (?, ?, SHA2(?, 256), ?, ?)";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getClave());
            ps.setString(4, u.getPerfil());
            ps.setBoolean(5, u.isActivo());
            ps.executeUpdate();
        }
    }

    /** Si la clave viene vacía, se deja la clave anterior. */
    public void actualizar(Usuario u) throws SQLException {
        boolean cambiarClave = u.getClave() != null && !u.getClave().isEmpty();
        String sql;
        if (cambiarClave) {
            sql = "UPDATE usuario SET nombre = ?, usuario = ?, perfil = ?, activo = ?, clave = SHA2(?, 256) WHERE id_usuario = ?";
        } else {
            sql = "UPDATE usuario SET nombre = ?, usuario = ?, perfil = ?, activo = ? WHERE id_usuario = ?";
        }
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getPerfil());
            ps.setBoolean(4, u.isActivo());
            if (cambiarClave) {
                ps.setString(5, u.getClave());
                ps.setInt(6, u.getIdUsuario());
            } else {
                ps.setInt(5, u.getIdUsuario());
            }
            ps.executeUpdate();
        }
    }

    public void eliminar(int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
        }
    }

    private Usuario leerFila(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setNombre(rs.getString("nombre"));
        u.setUsuario(rs.getString("usuario"));
        u.setPerfil(rs.getString("perfil"));
        u.setActivo(rs.getBoolean("activo"));
        return u;
    }
}
