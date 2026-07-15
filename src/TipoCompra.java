/**
 * Enumeracion que define los tipos de compra disponibles en el sistema.
 * 
 * Permite distinguir entre compras de entradas individuales y compras de kits
 * de entradas, facilitando el procesamiento y reporte de las diferentes modalidades
 * de venta.
 * 
 * @author Sistema de Gestion de Compras
 * @version 1.0
 * @since 2024
 */
public enum TipoCompra {
    
    /** Tipo de compra para entradas individuales de partidos */
    ENTRADA,
    
    /** Tipo de compra para paquetes o kits de multiples entradas */
    KIT
}
