/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.BoletaDAO;
import dao.EventoDAO;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Random;
import modelo.Boleta;
import modelo.Evento;
import modelo.Sesion;


/**
 *
 * @author Jean
 */

/** Lógica de la Taquilla: eventos y venta de boletos. */
public class ControladorTaquilla {
    
    private EventoDAO eventoDAO = new EventoDAO();
    private BoletaDAO boletaDAO = new BoletaDAO();
    private Random azar = new Random();

    /** Solo los eventos activos se pueden vender. */
    public ArrayList<Evento> listarEventos() throws SQLException {
        return eventoDAO.listarActivos();
    }

    /** Entradas que todavía se pueden vender. */
    public int entradasDisponibles(Evento ev) throws SQLException {
        return ev.getAforo() - eventoDAO.contarVendidas(ev.getIdEvento());
    }

    /**
     * Vende un boleto. Devuelve la boleta creada,
     * o null si el evento ya no tiene entradas.
     */
    public Boleta venderBoleto(Evento ev) throws SQLException {
        // Se prueba hasta 5 veces por si el código generado ya existía
        for (int intento = 0; intento < 5; intento++) {
            Boleta b = new Boleta();
            b.setIdEvento(ev.getIdEvento());
            b.setIdUsuario(Sesion.idActual());   // el cajero que está vendiendo
            b.setCodigoUnico(generarCodigo(ev.getIdEvento()));
            try {
                int filas = boletaDAO.insertarSiHayAforo(b);
                if (filas == 0) {
                    return null;                 // aforo lleno
                }
                b.setNombreEvento(ev.getNombre());
                return b;
            } catch (SQLIntegrityConstraintViolationException e) {
                // código repetido: el ciclo genera otro
            }
        }
        throw new SQLException("No se pudo generar un código único, intente de nuevo.");
    }

    /** Código tipo E3-482910 (E + número de evento + 6 dígitos al azar). Solo usa caracteres de Code 39. */
    private String generarCodigo(int idEvento) {
        int numero = 100000 + azar.nextInt(900000);
        return "E" + idEvento + "-" + numero;
    }
}
