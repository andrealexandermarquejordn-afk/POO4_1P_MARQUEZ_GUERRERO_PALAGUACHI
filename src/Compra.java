import java.util.Date;

/**
 * Clase que representa una compra realizada por un aficionado.
 * 
 * Una compra registra la adquisición de entradas o kits por parte de un aficionado,
 * incluyendo información como el tipo de compra, cantidad, fecha y valor pagado.
 * Mantiene un contador automático para generar códigos únicos de compra.
 * 
 * @author Sistema de Gestión de Compras
 * @version 1.0
 * @since 2024
 */
public class Compra {

    /** Contador estático para generar códigos únicos de compra */
    private static int contadorCompras = 0; 
    
    /** Código único identificador de la compra */
    private String codigoCompra;
    
    /** Tipo de compra (entrada o kit) */
    private TipoCompra tipo;
    
    /** Código de referencia del producto comprado */
    private String codigoReferencia;
    
    /** Fecha en que se realizó la compra */
    private Date fechaCompra;
    
    /** Cantidad de artículos comprados */
    private int cantidad;
    
    /** Valor total pagado por la compra */
    private double valorPagado;
    
    /** Código único del aficionado que realizó la compra */
    private String codigoAficionado;
    
    /**
     * Construye una nueva compra con auto-generación de código.
     * 
     * Incrementa el contador de compras y genera automáticamente un código único
     * con el formato "C" seguido de número secuencial con 3 dígitos.
     * La fecha de compra se establece como la fecha actual del sistema.
     * 
     * @param tipo El tipo de compra (entrada o kit)
     * @param codigoReferencia Código de referencia del producto comprado
     * @param cantidad Cantidad de artículos a comprar
     * @param valorPagado Valor total pagado por la compra
     * @param codigoAficionado Código del aficionado que realiza la compra
     */
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
    
    /**
     * Construye una nueva compra con código y fecha especificados.
     * 
     * Este constructor se utiliza para cargar compras existentes desde una fuente de datos.
     * Actualiza el contador de compras si el código cargado es superior al contador actual.
     * 
     * @param codigoCompra Código único de la compra a asignar
     * @param tipo El tipo de compra (entrada o kit)
     * @param codigoReferencia Código de referencia del producto comprado
     * @param fechaCompra Fecha en que se realizó la compra
     * @param cantidad Cantidad de artículos comprados
     * @param valorPagado Valor total pagado por la compra
     * @param codigoAficionado Código del aficionado que realizó la compra
     */
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
    
    /**
     * Obtiene el código único de la compra.
     * 
     * @return El código de compra en formato "CXXX" donde XXX es un número secuencial
     */
    public String getCodigoCompra(){ 
        return codigoCompra;
    }
    
    /**
     * Obtiene el tipo de compra.
     * 
     * @return El tipo de compra (entrada o kit)
     */
    public TipoCompra getTipo() { 
        return tipo;
    }
    
    /**
     * Obtiene el código de referencia del producto comprado.
     * 
     * @return El código de referencia (partido o kit)
     */
    public String getCodigoReferencia() { 
        return codigoReferencia;
    }
    
    /**
     * Obtiene la fecha en que se realizó la compra.
     * 
     * @return La fecha de la compra
     */
    public Date getFechaCompra() { 
        return fechaCompra;
    }
    
    /**
     * Obtiene la cantidad de artículos comprados.
     * 
     * @return La cantidad de artículos en la compra
     */
    public int getCantidad() { 
        return cantidad;
    }
    
    /**
     * Obtiene el valor total pagado por la compra.
     * 
     * @return El valor pagado en la transacción
     */
    public double getValorPagado() { 
        return valorPagado;
    }
    
    /**
     * Obtiene el código del aficionado que realizó la compra.
     * 
     * @return El código único del aficionado
     */
    public String getCodigoAficionado() { 
        return codigoAficionado;
    }

    /**
     * Establece el código único de la compra.
     * 
     * @param codigoCompra El código de compra a asignar
     */
    public void setCodigoCompra(String codigoCompra) {
        this.codigoCompra = codigoCompra;
    }
    
    /**
     * Establece el tipo de compra.
     * 
     * @param tipo El tipo de compra a asignar
     */
    public void setTipo(TipoCompra tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Establece el código de referencia del producto comprado.
     * 
     * @param codigoReferencia El código de referencia a asignar
     */
    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }
    
    /**
     * Establece la fecha de la compra.
     * 
     * @param fechaCompra La fecha a asignar
     */
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    /**
     * Establece la cantidad de artículos comprados.
     * 
     * @param cantidad La cantidad a asignar
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    /**
     * Establece el valor total pagado por la compra.
     * 
     * @param valorPagado El valor a asignar
     */
    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }
    
    /**
     * Establece el código del aficionado que realizó la compra.
     * 
     * @param codigoAficionado El código del aficionado a asignar
     */
    public void setCodigoAficionado(String codigoAficionado) {
        this.codigoAficionado = codigoAficionado;
    }
}
