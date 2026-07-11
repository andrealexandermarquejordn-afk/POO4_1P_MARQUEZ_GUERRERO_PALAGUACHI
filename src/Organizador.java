public class Organizador extends Usuario {
    private String empresa;
    private String cargo;

    public Organizador(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasena, String correo, String rol, String empresa, String cargo) {
        super(codigoUnico, cedula, nombres, apellidos, usuario, contrasena, correo, rol);
        this.empresa = empresa;
        this.cargo = cargo;
    }
    @Override
    public void consultarEntradas() {
        System.out.println("===== CONSULTAR TODAS LAS COMPRAS (Modo Organizador) =====");
        System.out.println("Organizador a cargo: " + getNombres() + " " + getApellidos() + " - " + this.cargo);
    }
    @Override
    public String toString() {
        return "Organizador: " + getNombres() + " " + getApellidos() + " | Empresa: " + empresa + " | Cargo: " + cargo;
    }
    public String getEmpresa() { 
        return empresa; 
    }
    public void setEmpresa(String empresa) { 
        this.empresa = empresa; 
    }
    public String getCargo() { 
        return cargo; 
    }
    public void setCargo(String cargo) { 
        this.cargo = cargo; 
    }
}
