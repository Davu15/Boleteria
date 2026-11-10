package modelo;

import java.sql.Date;

/** Representa un evento (concierto, obra, etc.). */
public class Evento {

    private int idEvento;
    private String nombre;
    private String categoria;
    private String descripcion;
    private Date fecha;
    private double precio;
    private int aforo;        // capacidad máxima de personas
    private boolean activo;   // si está activo se puede vender en la taquilla

    public Evento() {
    }

    public int getIdEvento() { return idEvento; }
    public void setIdEvento(int idEvento) { this.idEvento = idEvento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getAforo() { return aforo; }
    public void setAforo(int aforo) { this.aforo = aforo; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    // El ComboBox usa este texto para mostrar el evento
    @Override
    public String toString() {
        return nombre;
    }
}
