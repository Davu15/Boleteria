/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Timestamp;

/**
 *
 * @author Jean
 */

/** Representa una boleta vendida. */
public class Boleta {
    
    private int idBoleta;
    private int idEvento;
    private int idUsuario;         // quién la vendió
    private String nombreVendedor; // se llena cuando la consulta une boleta con usuario
    private String nombreEvento;   // se llena cuando la consulta une boleta con evento
    private String codigoUnico;    // el que se dibuja como código de barras
    private String estado;         // DISPONIBLE, USADA o ANULADA
    private Timestamp fechaVenta;

    public Boleta() {
    }

    public int getIdBoleta() { return idBoleta; }
    public void setIdBoleta(int idBoleta) { this.idBoleta = idBoleta; }

    public int getIdEvento() { return idEvento; }
    public void setIdEvento(int idEvento) { this.idEvento = idEvento; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreVendedor() { return nombreVendedor; }
    public void setNombreVendedor(String nombreVendedor) { this.nombreVendedor = nombreVendedor; }

    public String getNombreEvento() { return nombreEvento; }
    public void setNombreEvento(String nombreEvento) { this.nombreEvento = nombreEvento; }

    public String getCodigoUnico() { return codigoUnico; }
    public void setCodigoUnico(String codigoUnico) { this.codigoUnico = codigoUnico; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Timestamp getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(Timestamp fechaVenta) { this.fechaVenta = fechaVenta; }
}
