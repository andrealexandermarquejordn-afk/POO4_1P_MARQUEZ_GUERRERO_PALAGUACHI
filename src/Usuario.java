public abstract class Usuario {
    
    protected String codigoUnico;
    protected String cedula;
    protected String nombres;
    protected String apellidos;
    protected String username;
    protected String password;
    protected String correo;
    protected char rol;


public Usuario() {
    }

public Usuario(String codigoUnico, String cedula, String nombres, String apellidos, String username, String password, String correo, char rol) {
        this.codigoUnico = codigoUnico;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.rol = rol;
    }

public boolean iniciarSesion(String username, String password) {
        return false; 
    }
    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public char getRol() {
        return rol;
    }

    public String toString() {
        return "Usuario{" + "codigoUnico='" + codigoUnico + ", nombres='" + nombres + ", apellidos='" + apellidos + ", rol=" + rol +'}';
    }

    public abstract void consultarEntrada(Sistema sistema);
}
