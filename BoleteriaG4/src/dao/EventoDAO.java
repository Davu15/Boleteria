package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Evento;

/** Acceso a la tabla evento (solo SQL, nada de pantallas). */
public class EventoDAO {

    private static final String COLUMNAS = "id_evento, nombre, categoria, descripcion, fecha, precio, aforo, activo";

    /** Todos los eventos (para la ventana de gestión). */
    public ArrayList<Evento> listar() throws SQLException {
        return consultar("SELECT " + COLUMNAS + " FROM evento ORDER BY fecha, nombre");
    }

    /** Solo los eventos activos (para vender en la taquilla). */
    public ArrayList<Evento> listarActivos() throws SQLException {
        return consultar("SELECT " + COLUMNAS + " FROM evento WHERE activo = 1 ORDER BY fecha, nombre");
    }

    public void insertar(Evento ev) throws SQLException {
        String sql = "INSERT INTO evento (nombre, categoria, descripcion, fecha, precio, aforo, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, ev.getNombre());
            ps.setString(2, ev.getCategoria());
            ps.setString(3, ev.getDescripcion());
            ps.setDate(4, ev.getFecha());
            ps.setDouble(5, ev.getPrecio());
            ps.setInt(6, ev.getAforo());
            ps.setBoolean(7, ev.isActivo());
            ps.executeUpdate();
        }
    }

    public void actualizar(Evento ev) throws SQLException {
        String sql = "UPDATE evento SET nombre = ?, categoria = ?, descripcion = ?, fecha = ?, "
                + "precio = ?, aforo = ?, activo = ? WHERE id_evento = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, ev.getNombre());
            ps.setString(2, ev.getCategoria());
            ps.setString(3, ev.getDescripcion());
            ps.setDate(4, ev.getFecha());
            ps.setDouble(5, ev.getPrecio());
            ps.setInt(6, ev.getAforo());
            ps.setBoolean(7, ev.isActivo());
            ps.setInt(8, ev.getIdEvento());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idEvento) throws SQLException {
        String sql = "DELETE FROM evento WHERE id_evento = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idEvento);
            ps.executeUpdate();
        }
    }

    /** Cuántas boletas se han vendido (las anuladas no cuentan). */
    public int contarVendidas(int idEvento) throws SQLException {
        String sql = "SELECT COUNT(*) FROM boleta WHERE id_evento = ? AND estado <> 'ANULADA'";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idEvento);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    // Ejecuta un SELECT de eventos y arma la lista
    private ArrayList<Evento> consultar(String sql) throws SQLException {
        ArrayList<Evento> lista = new ArrayList<>();
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Evento ev = new Evento();
                ev.setIdEvento(rs.getInt("id_evento"));
                ev.setNombre(rs.getString("nombre"));
                ev.setCategoria(rs.getString("categoria"));
                ev.setDescripcion(rs.getString("descripcion"));
                ev.setFecha(rs.getDate("fecha"));
                ev.setPrecio(rs.getDouble("precio"));
                ev.setAforo(rs.getInt("aforo"));
                ev.setActivo(rs.getBoolean("activo"));
                lista.add(ev);
            }
        }
        return lista;
    }
}
