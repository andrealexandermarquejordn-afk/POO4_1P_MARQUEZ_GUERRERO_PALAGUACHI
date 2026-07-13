import java.util.ArrayList;

public class Kit {
    private String codigo;
    private String nombre;
    private String descripcion;
    private ArrayList<String> codigosPartidosIncluidos;
    private double precio;
    private int disponibles;

    public Kit(String codigo, String nombre, String descripcion, ArrayList<String> codigosPartidosIncluidos, double precio, int disponibles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigosPartidosIncluidos = codigosPartidosIncluidos;
        this.precio = precio;
        this.disponibles = disponibles;
    }

    //Metodos getters
    public String getCodigo() {
        return codigo; 
    }
    public String getNombre() { 
        return nombre; 
    }
    public String getDescripcion() { 
        return descripcion; 
    }
    public ArrayList<String> getCodigosPartidosIncluidos() { 
        return codigosPartidosIncluidos; 
    }
    public double getPrecio() { 
        return precio; 
    }
    public int getDisponibles() { 
        return disponibles; 
    }

    //Metodos setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setCodigosPartidosIncluidos(ArrayList<String> codigosPartidosIncluidos) {
        this.codigosPartidosIncluidos = codigosPartidosIncluidos;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setDisponibles(int disponibles) { 
        this.disponibles = disponibles; 
    }
}
