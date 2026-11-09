package modelo;

/** Persona que entra al sistema. El perfil define qué puede hacer. */
public class Usuario {

    public static final String ADMIN = "ADMIN";
    public static final String CAJERO = "CAJERO";
    public static final String PUERTA = "PUERTA";

    private int idUsuario;
    private String nombre;
    private String usuario;
    private String clave;     // solo se usa al crear o cambiar la clave
    private String perfil;
    private boolean activo;

    public Usuario() {
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
