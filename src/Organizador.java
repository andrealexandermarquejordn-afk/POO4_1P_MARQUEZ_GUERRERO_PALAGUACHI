public class Organizador extends Usuario {
    private String empresa;
    private String cargo;

    public Organizador(String codigoUnico, String cedula, String nombres, String apellidos, String username, String password, String correo, TipoUsuario rol, String empresa, String cargo) {
        super(codigoUnico, cedula, nombres, apellidos, username, password, correo, rol);
        this.empresa = empresa;
        this.cargo = cargo;
    }

    
    @Override
    public void consultarEntrada(Sistema sistema) {
        System.out.println("===== CONSULTAR TODAS LAS COMPRAS (Modo Organizador) =====");
        System.out.println("Organizador a cargo: " + getNombres() + " " + getApellidos() + " - " + this.cargo);

        boolean hayCompras = false;
        for (Compra c : sistema.getCompras()) {
            System.out.println("- Código de Compra: " + c.getCodigoCompra() + 
                    " | Tipo: " + c.getTipo() + 
                    " | Código Referencia: " + c.getCodigoReferencia() + 
                    " | Fecha: " + c.getFechaCompra() + 
                    " | Cantidad: " + c.getCantidad() + 
                    " | Valor Pagado: $" + String.format("%.2f", c.getValorPagado()) + 
                    " | Estado: Completada" + 
                    " | Código Aficionado: " + c.getCodigoAficionado());
            hayCompras = true;
        }
        if (!hayCompras) {
            System.out.println("No hay compras registradas en el sistema.");
        }
    }

    public void generarReporteVentas(Sistema sistema) {
        ReporteVenta reporte = sistema.generarReporte();
        System.out.println(reporte.toString());
        sistema.notificar(this, reporte);
    }
    
    @Override
    public String toString() {
        return "Organizador: " + getNombres() + " " + getApellidos() + " | Empresa: " + empresa + " | Cargo: " + cargo;
    }

//Metodos getters
    public String getEmpresa() { 
        return empresa; 
    }
    public String getCargo() { 
        return cargo; 
    }

    //Metodos setters
    public void setEmpresa(String empresa) { 
        this.empresa = empresa; 
    }
    public void setCargo(String cargo) { 
        this.cargo = cargo; 
    }
}