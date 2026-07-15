import java.util.Date;
public class Compra {

    private static int contadorCompras = 0;
    private String codigoCompra;
    private TipoCompra tipo;
    private String codigoReferencia;
    private Date fechaCompra;
    private int cantidad;
    private double valorPagado;
    private String codigoAficionado;
    
    public Compra(TipoCompra tipo, String codigoReferencia, int cantidad, double valorPagado, String codigoAficionado){
        contadorCompras++;
        this.codigoCompra = "C" + String.format("%03d", contadorCompras); 
        this.tipo = tipo;
        this.codigoReferencia = codigoReferencia;
        this.fechaCompra = new Date();
        this.cantidad = cantidad;
        this.valorPagado = valorPagado;
        this.codigoAficionado = codigoAficionado;
    }
    public Compra(String codigoCompra, TipoCompra tipo, String codigoReferencia, Date fechaCompra, int cantidad, double valorPagado, String codigoAficionado){
        this.codigoCompra = codigoCompra;
        this.tipo = tipo;
        this.codigoReferencia = codigoReferencia;
        this.fechaCompra = fechaCompra;
        this.cantidad = cantidad;
        this.valorPagado = valorPagado;
        this.codigoAficionado = codigoAficionado;
        int idNumerico = Integer.parseInt(codigoCompra.substring(1));
        if (idNumerico > contadorCompras){
            contadorCompras = idNumerico;
        }
    }
    //Metodos getters
    public String getCodigoCompra(){ 
        return codigoCompra;
    }
    public TipoCompra getTipo() { 
        return tipo;
    }
    public String getCodigoReferencia() { 
        return codigoReferencia;
    }
    public Date getFechaCompra() { 
        return fechaCompra;
    }
    public int getCantidad() { 
        return cantidad;
    }
    public double getValorPagado() { 
        return valorPagado;
    }
    public String getCodigoAficionado() { 
        return codigoAficionado;
    }

    //Metodos setters
    public void setCodigoCompra(String codigoCompra) {
        this.codigoCompra = codigoCompra;
    }
    public void setTipo(TipoCompra tipo) {
        this.tipo = tipo;
    }
    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }
    public void setCodigoAficionado(String codigoAficionado) {
        this.codigoAficionado = codigoAficionado;
    }
}
