/**
 * Enumeracion que define los tipos de usuarios del sistema.
 * 
 * Diferencia entre dos roles principales: Aficionados que pueden comprar entradas
 * y kits de partidos, y Organizadores que tienen permisos administrativos para
 * gestionar el sistema y generar reportes de ventas.
 * 
 * @author Sistema de Gestion de Usuarios
 * @version 1.0
 * @since 2024
 */
public enum TipoUsuario {
    
    /** Tipo de usuario Aficionado: comprador de entradas y kits */
    A,
    
    /** Tipo de usuario Organizador: administrador del sistema */
    O
}
