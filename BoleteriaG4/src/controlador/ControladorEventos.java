package controlador;

import dao.EventoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Evento;

/** Lógica de la ventana de gestión de eventos (agregar, modificar, eliminar, listar). */
public class ControladorEventos {

    private EventoDAO eventoDAO = new EventoDAO();

    public ArrayList<Evento> listar() throws SQLException {
        return eventoDAO.listar();
    }

    public void agregar(Evento ev) throws SQLException {
        eventoDAO.insertar(ev);
    }

    public void modificar(Evento ev) throws SQLException {
        eventoDAO.actualizar(ev);
    }

    public void eliminar(int idEvento) throws SQLException {
        eventoDAO.eliminar(idEvento);
    }
}
