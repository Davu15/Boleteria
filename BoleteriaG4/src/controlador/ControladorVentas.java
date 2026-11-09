package controlador;

import dao.BoletaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Boleta;
import modelo.Sesion;

/** Lógica de la ventana Ventas. */
public class ControladorVentas {

    private BoletaDAO boletaDAO = new BoletaDAO();

    /** El administrador ve todas las boletas; los demás solo las que vendieron ellos. */
    public ArrayList<Boleta> listar() throws SQLException {
        int filtro = Sesion.esAdmin() ? 0 : Sesion.idActual();
        return boletaDAO.listar(filtro);
    }

    /** Devuelve 1 si se anuló, 0 si la boleta ya no estaba DISPONIBLE. */
    public int anular(int idBoleta) throws SQLException {
        return boletaDAO.anular(idBoleta);
    }
}
