/**
 * Enumeracion que define las zonas o categorias de asientos disponibles en los estadios.
 * 
 * Cada zona representa un nivel de comodidad y precio diferente, permitiendo a los
 * aficionados elegir el tipo de entrada segun su presupuesto y preferencias. Las zonas
 * determinan los precios y la disponibilidad de entradas para cada partido.
 * 
 * @author Sistema de Gestion de Entradas
 * @version 1.0
 * @since 2024
 */
public enum Zona {
    
    /** Zona General: asientos basicos con precio economico */
    GENERAL,
    
    /** Zona Preferencial: asientos con mejor ubicacion a precio moderado */
    PREFERENCIAL,
    
    /** Zona VIP: asientos premium con maxima comodidad y visibilidad */
    VIP
}
