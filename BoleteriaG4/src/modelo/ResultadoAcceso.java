package modelo;

/** Resultado de leer un boleto en la puerta. */
public class ResultadoAcceso {

    public static final int APROBADO = 1;
    public static final int DENEGADO = 2;
    public static final int DUPLICADO = 3;

    private int tipo;
    private String mensaje;

    public ResultadoAcceso(int tipo, String mensaje) {
        this.tipo = tipo;
        this.mensaje = mensaje;
    }

    public int getTipo() { return tipo; }
    public String getMensaje() { return mensaje; }
}
