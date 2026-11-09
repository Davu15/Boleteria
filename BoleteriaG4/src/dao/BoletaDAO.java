/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Boleta;

/**
 *
 * @author Jean
 */

/** Acceso a la tabla boleta (solo SQL, nada de pantallas). */
public class BoletaDAO {
    
    // Consulta base: une la boleta con su evento y con el usuario que la vendió
    private static final String SELECT_BASE =
            "SELECT b.id_boleta, b.id_evento, e.nombre, b.id_usuario, u.nombre AS vendedor, "
            + "b.codigo_unico, b.estado, b.fecha_venta "
            + "FROM boleta b "
            + "INNER JOIN evento e ON e.id_evento = b.id_evento "
            + "INNER JOIN usuario u ON u.id_usuario = b.id_usuario ";

    /**
     * Guarda la boleta SOLO si el evento está activo y todavía tiene aforo.
     * Todo se hace en una sola sentencia para que dos cajeros
     * no puedan vender la última entrada al mismo tiempo.
     * Devuelve 1 si se guardó, 0 si no se pudo vender.
     */
    public int insertarSiHayAforo(Boleta b) throws SQLException {
        String sql = "INSERT INTO boleta (id_evento, id_usuario, codigo_unico) "
                + "SELECT e.id_evento, ?, ? FROM evento e "
                + "WHERE e.id_evento = ? AND e.activo = 1 "
                + "AND (SELECT COUNT(*) FROM boleta x WHERE x.id_evento = e.id_evento AND x.estado <> 'ANULADA') < e.aforo";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, b.getIdUsuario());
            ps.setString(2, b.getCodigoUnico());
            ps.setInt(3, b.getIdEvento());
            return ps.executeUpdate();
        }
    }

    /**
     * Sentencia ATÓMICA del informe: verifica y marca como USADA en un solo paso.
     * También guarda la hora y quién la validó en la puerta.
     * Devuelve 1 si el acceso se concede, 0 si no se pudo.
     */
    public int marcarUsada(String codigo, int idUsuarioPuerta) throws SQLException {
        String sql = "UPDATE boleta SET estado = 'USADA', fecha_uso = NOW(), id_usuario_puerta = ? "
                + "WHERE codigo_unico = ? AND estado = 'DISPONIBLE'";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idUsuarioPuerta);
            ps.setString(2, codigo);
            return ps.executeUpdate();
        }
    }

    /** Busca una boleta por su código. Devuelve null si no existe. */
    public Boleta buscarPorCodigo(String codigo) throws SQLException {
        String sql = SELECT_BASE + "WHERE b.codigo_unico = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return leerFila(rs);
                }
            }
        }
        return null;
    }

    /** Lista las boletas. Si idUsuario es 0 trae todas; si no, solo las vendidas por ese usuario. */
    public ArrayList<Boleta> listar(int idUsuario) throws SQLException {
        ArrayList<Boleta> lista = new ArrayList<>();
        String sql = SELECT_BASE + "WHERE (? = 0 OR b.id_usuario = ?) ORDER BY b.id_boleta DESC";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(leerFila(rs));
                }
            }
        }
        return lista;
    }

    /** Anula una boleta que todavía no se ha usado. Devuelve las filas cambiadas. */
    public int anular(int idBoleta) throws SQLException {
        String sql = "UPDATE boleta SET estado = 'ANULADA' WHERE id_boleta = ? AND estado = 'DISPONIBLE'";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idBoleta);
            return ps.executeUpdate();
        }
    }

    // Convierte una fila del ResultSet en un objeto Boleta
    private Boleta leerFila(ResultSet rs) throws SQLException {
        Boleta b = new Boleta();
        b.setIdBoleta(rs.getInt("id_boleta"));
        b.setIdEvento(rs.getInt("id_evento"));
        b.setNombreEvento(rs.getString("nombre"));
        b.setIdUsuario(rs.getInt("id_usuario"));
        b.setNombreVendedor(rs.getString("vendedor"));
        b.setCodigoUnico(rs.getString("codigo_unico"));
        b.setEstado(rs.getString("estado"));
        b.setFechaVenta(rs.getTimestamp("fecha_venta"));
        return b;
    }
}
