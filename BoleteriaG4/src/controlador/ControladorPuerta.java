package controlador;

import dao.BoletaDAO;
import java.sql.SQLException;
import modelo.Boleta;
import modelo.ResultadoAcceso;
import modelo.Sesion;

/** Lógica de la Puerta: valida el código leído. */
public class ControladorPuerta {

    private BoletaDAO boletaDAO = new BoletaDAO();

    public ResultadoAcceso validar(String codigo) throws SQLException {
        // Quita cualquier carácter que no sea letra o número (el lector puede meter ' o ´ según el teclado)
        codigo = codigo.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        // Un solo UPDATE decide si entra o no (evita que dos puertas lo acepten a la vez)
        int filas = boletaDAO.marcarUsada(codigo, Sesion.idActual());
        Boleta b = boletaDAO.buscarPorCodigo(codigo);

        if (filas == 1) {
            return new ResultadoAcceso(ResultadoAcceso.APROBADO, b.getNombreEvento());
        }

        // No se pudo usar: averiguamos el motivo
        if (b == null) {
            return new ResultadoAcceso(ResultadoAcceso.DENEGADO, "El boleto no existe");
        }
        if (b.getEstado().equals("USADA")) {
            return new ResultadoAcceso(ResultadoAcceso.DUPLICADO, "Este boleto ya fue usado");
        }
        return new ResultadoAcceso(ResultadoAcceso.DENEGADO, "El boleto está anulado");
    }
}
