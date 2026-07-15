/**
 * Clase que representa a un organizador del mundial de fútbol.
 * 
 * Un organizador es un usuario con permisos administrativos que puede consultar
 * todas las compras realizadas en el sistema y generar reportes de ventas.
 * Posee información adicional como empresa a la que pertenece y cargo que desempeña.
 * Extiende la clase Usuario e implementa funcionalidades de administración.
 * 
 * @author Sistema de Gestión de Organizadores
 * @version 1.0
 * @since 2024
 */
public class Organizador extends Usuario {
    
    /** Nombre de la empresa a la que pertenece el organizador */
    private String empresa;
    
    /** Cargo que desempeña el organizador en la organización */
    private String cargo;

    /**
     * Construye un nuevo objeto Organizador con la información completa.
     * 
     * @param codigoUnico Código único identificador del organizador
     * @param cedula Número de cédula del organizador
     * @param nombres Nombres del organizador
     * @param apellidos Apellidos del organizador
     * @param username Nombre de usuario para autenticación
     * @param password Contraseña para autenticación
     * @param correo Correo electrónico del organizador
     * @param rol Tipo de usuario (rol) del organizador
     * @param empresa Nombre de la empresa a la que pertenece
     * @param cargo Cargo que desempeña
     */
    public Organizador(String codigoUnico, String cedula, String nombres, String apellidos, String username, String password, String correo, TipoUsuario rol, String empresa, String cargo) {
        super(codigoUnico, cedula, nombres, apellidos, username, password, correo, rol);
        this.empresa = empresa;
        this.cargo = cargo;
    }

    /**
     * Consulta y muestra todas las compras registradas en el sistema.
     * 
     * Muestra un listado completo de todas las compras realizadas con detalles como
     * código, tipo, referencia, fecha, cantidad, valor y aficionado que las realizó.
     * Esta funcionalidad es exclusiva de los organizadores con permisos administrativos.
     * 
     * @param sistema Instancia del sistema donde se almacenan las compras
     */
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

    /**
     * Genera un reporte completo de ventas del sistema.
     * 
     * Crea un reporte con información consolidada de todas las ventas realizadas
     * y lo muestra en consola. Además, envía una notificación del reporte al organizador.
     * 
     * @param sistema Instancia del sistema para obtener los datos de ventas
     */
    public void generarReporteVentas(Sistema sistema) {
        ReporteVenta reporte = sistema.generarReporte();
        System.out.println(reporte.toString());
        sistema.notificar(this, reporte);
    }
    
    /**
     * Retorna una representación en string del organizador.
     * 
     * @return String con los datos principales del organizador (nombres, apellidos, empresa y cargo)
     */
    @Override
    public String toString() {
        return "Organizador: " + getNombres() + " " + getApellidos() + " | Empresa: " + empresa + " | Cargo: " + cargo;
    }

    /**
     * Obtiene el nombre de la empresa del organizador.
     * 
     * @return El nombre de la empresa
     */
    public String getEmpresa() { 
        return empresa; 
    }
    
    /**
     * Obtiene el cargo del organizador.
     * 
     * @return El cargo que desempeña
     */
    public String getCargo() { 
        return cargo; 
    }

    /**
     * Establece el nombre de la empresa del organizador.
     * 
     * @param empresa El nombre de la empresa a asignar
     */
    public void setEmpresa(String empresa) { 
        this.empresa = empresa; 
    }
    
    /**
     * Establece el cargo del organizador.
     * 
     * @param cargo El cargo a asignar
     */
    public void setCargo(String cargo) { 
        this.cargo = cargo; 
    }
}