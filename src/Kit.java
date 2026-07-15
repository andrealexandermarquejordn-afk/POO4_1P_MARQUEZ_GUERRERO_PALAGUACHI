import java.util.ArrayList;

/**
 * Clase que representa un kit de entradas para partidos.
 * 
 * Un kit es un paquete que agrupa múltiples partidos en una sola compra,
 * permitiendo a los aficionados comprar entradas para varios partidos de manera
 * más económica. Contiene información sobre código, nombre, descripción,
 * partidos incluidos, precio y disponibilidad.
 * 
 * @author Sistema de Gestión de Kits
 * @version 1.0
 * @since 2024
 */
public class Kit {
    
    /** Código único identificador del kit */
    private String codigo;
    
    /** Nombre descriptivo del kit */
    private String nombre;
    
    /** Descripción detallada del contenido del kit */
    private String descripcion;
    
    /** Lista de códigos de partidos incluidos en el kit */
    private ArrayList<String> codigosPartidosIncluidos;
    
    /** Precio del kit */
    private double precio;
    
    /** Cantidad de kits disponibles para compra */
    private int disponibles;

    /**
     * Construye un nuevo kit con la información completa.
     * 
     * @param codigo Código único identificador del kit
     * @param nombre Nombre del kit
     * @param descripcion Descripción del contenido y beneficios del kit
     * @param codigosPartidosIncluidos Lista de códigos de los partidos que incluye el kit
     * @param precio Precio de venta del kit
     * @param disponibles Cantidad de kits disponibles en inventario
     */
    public Kit(String codigo, String nombre, String descripcion, ArrayList<String> codigosPartidosIncluidos, double precio, int disponibles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigosPartidosIncluidos = codigosPartidosIncluidos;
        this.precio = precio;
        this.disponibles = disponibles;
    }

    /**
     * Obtiene el código único del kit.
     * 
     * @return El código del kit
     */
    public String getCodigo() {
        return codigo; 
    }
    
    /**
     * Obtiene el nombre del kit.
     * 
     * @return El nombre del kit
     */
    public String getNombre() { 
        return nombre; 
    }
    
    /**
     * Obtiene la descripción del kit.
     * 
     * @return La descripción del kit
     */
    public String getDescripcion() { 
        return descripcion; 
    }
    
    /**
     * Obtiene la lista de códigos de partidos incluidos en el kit.
     * 
     * @return ArrayList con los códigos de los partidos
     */
    public ArrayList<String> getCodigosPartidosIncluidos() { 
        return codigosPartidosIncluidos; 
    }
    
    /**
     * Obtiene el precio del kit.
     * 
     * @return El precio del kit
     */
    public double getPrecio() { 
        return precio; 
    }
    
    /**
     * Obtiene la cantidad de kits disponibles.
     * 
     * @return El número de kits disponibles para compra
     */
    public int getDisponibles() { 
        return disponibles; 
    }

    /**
     * Establece el código del kit.
     * 
     * @param codigo El código del kit a asignar
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    /**
     * Establece el nombre del kit.
     * 
     * @param nombre El nombre del kit a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Establece la descripción del kit.
     * 
     * @param descripcion La descripción del kit a asignar
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Establece la lista de códigos de partidos incluidos en el kit.
     * 
     * @param codigosPartidosIncluidos ArrayList con los códigos de los partidos a asignar
     */
    public void setCodigosPartidosIncluidos(ArrayList<String> codigosPartidosIncluidos) {
        this.codigosPartidosIncluidos = codigosPartidosIncluidos;
    }
    
    /**
     * Establece el precio del kit.
     * 
     * @param precio El precio del kit a asignar
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * Establece la cantidad de kits disponibles.
     * 
     * @param disponibles El número de kits disponibles a asignar
     */
    public void setDisponibles(int disponibles) { 
        this.disponibles = disponibles; 
    }
}
