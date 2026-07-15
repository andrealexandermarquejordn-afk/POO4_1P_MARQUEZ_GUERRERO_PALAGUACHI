/**
 * Clase que representa a un aficionado del sistema.
 * 
 * Un aficionado es un usuario que puede comprar entradas para partidos
 * y tiene información adicional como número de celular y país favorito.
 * Extiende la clase Usuario e implementa funcionalidades específicas
 * de consulta de entradas compradas.
 * 
 * @author Sistema de Gestión de Aficionados
 * @version 1.0
 * @since 2024
 */
public class Aficionado extends Usuario {
    
    /** Número de celular del aficionado */
    private String celular;
    
    /** País favorito del aficionado */
    private String paisFavorito;
    
    /**
     * Construye un nuevo objeto Aficionado con la información completa.
     * 
     * @param codigoUnico Código único identificador del aficionado
     * @param cedula Número de cédula del aficionado
     * @param nombres Nombres del aficionado
     * @param apellidos Apellidos del aficionado
     * @param username Nombre de usuario para autenticación
     * @param password Contraseña para autenticación
     * @param correo Correo electrónico del aficionado
     * @param rol Tipo de usuario (rol) del aficionado
     * @param celular Número de celular del aficionado
     * @param paisFavorito País favorito del aficionado
     */
    public Aficionado(String codigoUnico, String cedula, String nombres, String apellidos, String username, String password, String correo, TipoUsuario rol, String celular, String paisFavorito){
        super(codigoUnico, cedula, nombres, apellidos, username, password, correo, rol);
        this.celular = celular;
        this.paisFavorito = paisFavorito;
    }

    /**
     * Consulta y muestra todas las compras registradas del aficionado.
     * 
     * Busca en el sistema todas las compras asociadas a este aficionado
     * mediante su código único y las muestra con detalles como código de compra,
     * fecha y valor pagado. Si no tiene compras, muestra un mensaje indicándolo.
     * 
     * @param sistema Instancia del sistema donde se almacenan las compras
     */
    @Override
    public void consultarEntrada(Sistema sistema){
        System.out.println("Mis Compras (Aficionado: " + getNombres() + ")");
        boolean tieneCompras = false;
        for (Compra c : sistema.getCompras()) {
            if (c.getCodigoAficionado().equals(this.codigoUnico)) {
                System.out.println("- Código de Compra: " + c.getCodigoCompra() + " | Fecha: " + c.getFechaCompra() + " | Total: $" + c.getValorPagado());
                tieneCompras = true;
            }
        }
        if (!tieneCompras) {
            System.out.println("No tienes compras registradas.");
        }
    }

    /**
     * Obtiene el número de celular del aficionado.
     * 
     * @return El número de celular del aficionado
     */
    public String getCelular(){
        return celular; 
    }
    
    /**
     * Obtiene el país favorito del aficionado.
     * 
     * @return El país favorito del aficionado
     */
    public String getPaisFavorito(){
        return paisFavorito; 
    }

    /**
     * Establece el número de celular del aficionado.
     * 
     * @param celular El nuevo número de celular a asignar
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    /**
     * Establece el país favorito del aficionado.
     * 
     * @param paisFavorito El nuevo país favorito a asignar
     */
    public void setPaisFavorito(String paisFavorito) {
        this.paisFavorito = paisFavorito;
    }
}
