/**
 * Clase abstracta que define la estructura base de todos los usuarios del sistema.
 * 
 * Proporciona los atributos comunes y metodos para la gestion de usuarios, sirviendo
 * como clase padre para Aficionado y Organizador. Incluye funcionalidades de autenticacion,
 * almacenamiento de datos personales y un metodo abstracto para consultar entradas segun
 * el tipo de usuario.
 * 
 * @author Sistema de Gestion de Usuarios
 * @version 1.0
 * @since 2024
 */
public abstract class Usuario {

    /** Codigo unico identificador del usuario en el sistema */
    protected String codigoUnico;
    protected String cedula;
    protected String nombres;
    protected String apellidos;
    protected String username;
    protected String password;
    protected String correo;
    protected TipoUsuario rol;
  
    /**
     * Constructor sin parametros para Usuario.
     */
    public Usuario() {
    }

    /**
     * Constructor parametrizado para Usuario.
     * 
     * @param codigoUnico el codigo unico del usuario
     * @param cedula el numero de cedula del usuario
     * @param nombres los nombres del usuario
     * @param apellidos los apellidos del usuario
     * @param username el nombre de usuario para inicio de sesion
     * @param password la contraseña del usuario
     * @param correo el correo electronico del usuario
     * @param rol el tipo de usuario (Aficionado u Organizador)
     */
    public Usuario(String codigoUnico, String cedula, String nombres, String apellidos, String username, String password, String correo, TipoUsuario rol) {
        this.codigoUnico = codigoUnico;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.rol = rol;
    }

    /**
     * Establece el codigo unico del usuario.
     * 
     * @param codigoUnico el codigo unico a asignar
     */
    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }
    /**
     * Establece el numero de cedula del usuario.
     * 
     * @param cedula el numero de cedula a asignar
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    /**
     * Establece los nombres del usuario.
     * 
     * @param nombres los nombres a asignar
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    /**
     * Establece los apellidos del usuario.
     * 
     * @param apellidos los apellidos a asignar
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    /**
     * Establece el nombre de usuario para inicio de sesion.
     * 
     * @param username el nombre de usuario a asignar
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Establece la contraseña del usuario.
     * 
     * @param password la contraseña a asignar
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Establece el correo electronico del usuario.
     * 
     * @param correo el correo electronico a asignar
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    /**
     * Obtiene el codigo unico del usuario.
     * 
     * @return el codigo unico del usuario
     */
    public String getCodigoUnico() {
        return codigoUnico;
    }
    /**
     * Obtiene el numero de cedula del usuario.
     * 
     * @return el numero de cedula del usuario
     */
    public String getCedula() {
        return cedula;
    }
    
    /**
     * Obtiene los nombres del usuario.
     * 
     * @return los nombres del usuario
     */
    public String getNombres() {
        return nombres;
    }
    /**
     * Obtiene los apellidos del usuario.
     * 
     * @return los apellidos del usuario
     */
    public String getApellidos() {
        return apellidos;
    }
    
    /**
     * Obtiene el nombre de usuario para inicio de sesion.
     * 
     * @return el nombre de usuario
     */
    public String getUsername() {
        return username;
    }
    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return la contraseña del usuario
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Obtiene el correo electronico del usuario.
     * 
     * @return el correo electronico del usuario
     */
    public String getCorreo() {
        return correo;
    }
    /**
     * Obtiene el tipo de usuario.
     * 
     * @return el tipo de usuario (Aficionado u Organizador)
     */
    public TipoUsuario getRol() {
        return rol;
    }

    /**
     * Retorna una representacion en cadena del usuario.
     * 
     * @return cadena con informacion del usuario
     */
    public String toString() {
        return "Usuario{" + "codigoUnico='" + codigoUnico + ", nombres='" + nombres + ", apellidos='" + apellidos
                + ", rol=" + rol + '}';
    }

    /**
     * Metodo abstracto para consultar entradas disponibles.
     * 
     * La implementacion varia segun el tipo de usuario: Aficionado puede consultar
     * entradas disponibles por fase, mientras que Organizador puede ver todas las
     * entradas del sistema.
     * 
     * @param sistema la instancia del sistema de gestion
     */
    public abstract void consultarEntrada(Sistema sistema);
}
