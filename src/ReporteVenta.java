import java.util.Date;
public class ReporteVenta {
    private int totalCompras;
    private int totalEntrada;
    private int totalKit;
    private double montoTotal;
    private Date fechaReporte;

    // Constructor
    public ReporteVenta(int totalCompras, int totalEntrada, int totalKit, double montoTotal, Date fechaReporte) {
        this.totalCompras = totalCompras;
        this.totalEntrada = totalEntrada;
        this.totalKit = totalKit;
        this.montoTotal = montoTotal;
        this.fechaReporte = fechaReporte;
    }

    //Metodos getters
    public int getTotalCompras() { 
        return totalCompras; 
    }
    public int getTotalEntrada() { 
        return totalEntrada; 
    }
    public int getTotalKit() { 
        return totalKit; 
    }
    public double getMontoTotal() { 
        return montoTotal; 
    }
    public Date getFechaReporte() { 
        return fechaReporte; 
    }
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
