import java.util.Date;

/**
 * Clase que representa un reporte de ventas del sistema.
 * 
 * Contiene informacion consolidada sobre el total de compras realizadas, distribuidas
 * por tipo (entradas individuales vs kits), el monto total recaudado y la fecha
 * en que se genero el reporte. Proporciona metodos para acceder a esta informacion
 * y una representacion textual del reporte.
 * 
 * @author Sistema de Reportes de Ventas
 * @version 1.0
 * @since 2024
 */
public class ReporteVenta {
    
    /** Numero total de compras realizadas */
    private int totalCompras;
    
    /** Numero total de compras de entradas individuales */
    private int totalEntrada;
    
    /** Numero total de compras de kits */
    private int totalKit;
    
    /** Monto total recaudado en todas las compras */
    private double montoTotal;
    
    /** Fecha en que se genero el reporte */
    private Date fechaReporte;

    /**
     * Construye un nuevo reporte de ventas con la informacion completa.
     * 
     * @param totalCompras Numero total de compras realizadas
     * @param totalEntrada Numero total de compras de entradas individuales
     * @param totalKit Numero total de compras de kits
     * @param montoTotal Monto total recaudado
     * @param fechaReporte Fecha en que se genero el reporte
     */
    public ReporteVenta(int totalCompras, int totalEntrada, int totalKit, double montoTotal, Date fechaReporte) {
        this.totalCompras = totalCompras;
        this.totalEntrada = totalEntrada;
        this.totalKit = totalKit;
        this.montoTotal = montoTotal;
        this.fechaReporte = fechaReporte;
    }

    /**
     * Obtiene el numero total de compras.
     * 
     * @return El total de compras realizadas
     */
    public int getTotalCompras() { 
        return totalCompras; 
    }
    
    /**
     * Obtiene el numero de compras de entradas individuales.
     * 
     * @return El total de compras de entradas
     */
    public int getTotalEntrada() { 
        return totalEntrada; 
    }
    
    /**
     * Obtiene el numero de compras de kits.
     * 
     * @return El total de compras de kits
     */
    public int getTotalKit() { 
        return totalKit; 
    }
    
    /**
     * Obtiene el monto total recaudado.
     * 
     * @return El monto total en dinero recaudado
     */
    public double getMontoTotal() { 
        return montoTotal; 
    }
    
    /**
     * Obtiene la fecha de generacion del reporte.
     * 
     * @return La fecha en que se genero el reporte
     */
    public Date getFechaReporte() { 
        return fechaReporte; 
    }

    /**
     * Retorna una representacion textual formateada del reporte de ventas.
     * 
     * Genera un resumen legible que incluye el total de compras, distribucion
     * entre entradas individuales y kits, y el monto total recaudado con formato
     * de dos decimales para moneda.
     * 
     * @return String con el contenido formateado del reporte
     */
    @Override
    public String toString() {
        return "===== GENERAR REPORTE DE VENTAS =====\n" +
               "Resumen de ventas registradas:\n" +
               "Total de compras: " + totalCompras + "\n" +
               "Compras por tipo:\n" +
               "  Entradas: " + totalEntrada + "\n" +
               "  Kits: " + totalKit + "\n" +
               "Monto total recaudado:\n" +
               "  $" + String.format("%.2f", montoTotal);
    }
}
