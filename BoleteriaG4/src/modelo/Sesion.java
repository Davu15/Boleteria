package modelo;

/** Guarda quién inició sesión, para que todas las ventanas lo puedan consultar. */
public class Sesion {

    public static Usuario actual = null;

    public static boolean esAdmin() {
        return actual != null && actual.getPerfil().equals(Usuario.ADMIN);
    }

    public static boolean esCajero() {
        return actual != null && actual.getPerfil().equals(Usuario.CAJERO);
    }

    public static boolean esPuerta() {
        return actual != null && actual.getPerfil().equals(Usuario.PUERTA);
    }

    /** Número del usuario actual (0 si no hay sesión). */
    public static int idActual() {
        return actual == null ? 0 : actual.getIdUsuario();
    }
}
