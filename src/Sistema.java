import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Usuario> usuarios;
    private List<Partido> partidos;
    private List<Kit> kits;
    private List<Compra> compras;

    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.kits = new ArrayList<>();
        this.compras = new ArrayList<>();
    }
    //Metodos getters
    public List<Usuario> getUsuarios() { 
        return usuarios; 
    }
    public List<Partido> getPartidos() { 
        return partidos; 
    }
    public List<Kit> getKits() { 
        return kits; 
    }
    public List<Compra> getCompras() { 
        return compras; 
    }
    //Metodos setters
    public void setUsuarios(List<Usuario> usuarios) { 
        this.usuarios = usuarios; 
    }
    public void setPartidos(List<Partido> partidos) { 
        this.partidos = partidos; 
    }
    public void setKits(List<Kit> kits) { 
        this.kits = kits; 
    }
    public void setCompras(List<Compra> compras) { 
        this.compras = compras; 
    }

    public void cargarUsuarios() {
        System.out.println("Cargando usuarios desde archivos...");
    }

    public void cargarPartidos() {
        System.out.println("Cargando partidos desde partidos.txt...");
    }

    public void cargarKits() {
        System.out.println("Cargando kits desde kits.txt...");
    }

    public void registrarCompra(Compra compra) {
        if (compra != null) {
            this.compras.add(compra);
            System.out.println("Compra registrada en el sistema con éxito.");
        }
    }

    public Partido buscarPartido(String codigo) {
        for (Partido p : partidos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }
    
    public Kit buscarKit(String codigo) {
        for (Kit k : kits) {
            if (k.getCodigo().equalsIgnoreCase(codigo)) {
                return k;
            }
        }
        return null;
    }

    public void notificar(Aficionado aficionado, Compra compra) {
        System.out.println("De: correoSistema@mundial2026.com");
        System.out.println("Para: " + aficionado.getCorreo());
        System.out.println("Asunto: Compra de entrada realizada");
        System.out.println("Estimado/a " + aficionado.getNombres() + " " + aficionado.getApellidos() + ",");
        System.out.println("Su compra ha sido registrada exitosamente con el código " + compra.getCodigo() + " el día " + compra.getFechaCompra() + ".");
        Partido partido = buscarPartido(compra.getCodigoReferencia());
        if (partido != null) {
            System.out.println("Partido: " + partido.getSeleccionLocal() + " vs " + partido.getSeleccionVisitante());
            System.out.println("Código del partido: " + partido.getCodigo());
        }
        
        System.out.println("Cantidad: " + compra.getCantidad());
        System.out.println("Valor pagado: $" + String.format("%.2f", compra.getValorPagado()));
        System.out.println("Gracias por adquirir sus entradas para el Mundial. Recuerde conservar el código de compra para futuras consultas.");
        System.out.println("------------------------------------------------");
    }
    public void notificar(Aficionado aficionado, Compra compra, Kit kit) {
        System.out.println("De: correoSistema@mundial2026.com");
        System.out.println("Para: " + aficionado.getCorreo());
        System.out.println("Asunto: Compra de Kit de Entradas realizada");
        System.out.println("Estimado/a " + aficionado.getNombres() + " " + aficionado.getApellidos() + ",");
        System.out.println("Su compra de paquete ha sido registrada exitosamente con el código " + compra.getCodigo() + " el día " + compra.getFechaCompra() + ".");
        System.out.println("Detalle del Kit Adquirido: " + kit.getNombre() + " (" + kit.getDescripcion() + ")");
        System.out.println("Cantidad de Kits: " + compra.getCantidad());
        System.out.println("Valor pagado: $" + String.format("%.2f", compra.getValorPagado()));
        System.out.println("Gracias por adquirir sus paquetes oficiales para el Mundial. ¡Disfrute el evento!");
        System.out.println("--------------------------------------------");
    }

    public void notificar(Organizador organizador, ReporteVenta reporte) {
        System.out.println("De: correoSistema@mundial2026.com");
        System.out.println("Para: " + organizador.getCorreo());
        System.out.println("Asunto: Reporte de compras registradas");
        System.out.println("Estimado/a " + organizador.getNombres() + " " + organizador.getApellidos() + ",");
        System.out.println("Se ha generado el reporte de compras del sistema.");
        System.out.println("Fecha de generación del reporte: " + reporte.getFechaReporte());
        System.out.println("Total de compras registradas: " + reporte.getTotalCompras());
        System.out.println("Total de compras de entradas individuales: " + reporte.getTotalEntrada());
        System.out.println("Total de compras de kits: " + reporte.getTotalKit());
        System.out.println("Monto total recaudado: $" + String.format("%.2f", reporte.getMontoTotal()));
        System.out.println("Este reporte corresponde a las compras registradas en el archivo compras.txt.");
        System.out.println("-----------------------------------------------------");
    }
}