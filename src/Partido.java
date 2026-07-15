import java.util.Date;

/**
 * Clase que representa un partido de futbol del mundial 2026.
 * 
 * Contiene informacion completa del partido incluyendo los equipos que participan,
 * fecha, lugar de celebracion, capacidad del estadio, fase del torneo, disponibilidad
 * de entradas por zona (general, preferencial, VIP) y precios para cada zona.
 * Proporciona metodos para consultar y descontar stock de entradas.
 * 
 * @author Sistema de Gestion de Partidos
 * @version 1.0
 * @since 2024
 */
public class Partido {
    
    /** Codigo unico identificador del partido */
    private String codigo;
    
    /** Seleccion que actua como local en el partido */
    private String seleccionLocal;
    
    /** Seleccion que actua como visitante en el partido */
    private String seleccionVisitante;
    
    /** Fecha y hora en que se jugara el partido */
    private Date fecha;
    
    /** Nombre del estadio donde se jugara el partido */
    private String estadio;
    
    /** Ciudad donde se ubica el estadio */
    private String ciudad;
    
    /** Capacidad total del estadio */
    private int capacidad;
    
    /** Fase del torneo (grupos, octavos, cuartos, etc.) */
    private String fase;
    
    /** Numero de entradas disponibles en la zona general */
    private int entradasGeneralDisponibles;
    
    /** Numero de entradas disponibles en la zona preferencial */
    private int entradasPrefencialDisponibles;
    
    /** Numero de entradas disponibles en la zona VIP */
    private int entradasVipDisponibles;
    
    /** Precio unitario de las entradas de la zona general */
    private double precioGeneral;
    
    /** Precio unitario de las entradas de la zona preferencial */
    private double precioPrefencial;
    
    /** Precio unitario de las entradas de la zona VIP */
    private double precioVip;

    /**
     * Construye un nuevo partido con la informacion completa.
     * 
     * @param codigo Codigo unico identificador del partido
     * @param seleccionLocal Seleccion del equipo local
     * @param seleccionVisitante Seleccion del equipo visitante
     * @param fecha Fecha y hora del partido
     * @param estadio Nombre del estadio
     * @param ciudad Ciudad donde se juega
     * @param capacidad Capacidad total del estadio
     * @param fase Fase del torneo
     * @param entradasGeneralDisponibles Entradas disponibles zona general
     * @param entradasPrefencialDisponibles Entradas disponibles zona preferencial
     * @param entradasVipDisponibles Entradas disponibles zona VIP
     * @param precioGeneral Precio unitario zona general
     * @param precioPrefencial Precio unitario zona preferencial
     * @param precioVip Precio unitario zona VIP
     */
    public Partido(String codigo,String seleccionLocal,String seleccionVisitante,Date fecha,String estadio,String ciudad,int capacidad,String fase,int entradasGeneralDisponibles,int entradasPrefencialDisponibles,int entradasVipDisponibles,double precioGeneral,double precioPrefencial, double precioVip){
        this.codigo=codigo;
        this.seleccionLocal=seleccionLocal;
        this.seleccionVisitante=seleccionVisitante;
        this.fecha=fecha;
        this.estadio=estadio;
        this.ciudad=ciudad;
        this.capacidad=capacidad;
        this.fase=fase;
        this.entradasGeneralDisponibles=entradasGeneralDisponibles;
        this.entradasPrefencialDisponibles=entradasPrefencialDisponibles;
        this.entradasVipDisponibles=entradasVipDisponibles;
        this.precioGeneral=precioGeneral;
        this.precioPrefencial=precioPrefencial;
        this.precioVip=precioVip;
    }
    
    /**
     * Obtiene el codigo del partido.
     * 
     * @return El codigo unico del partido
     */
    public String getCodigo(){
        return this.codigo;
    }
    
    /**
     * Obtiene la seleccion local del partido.
     * 
     * @return El nombre de la seleccion local
     */
    public String getSeleccionLocal(){
        return this.seleccionLocal;
    }
    
    /**
     * Obtiene la seleccion visitante del partido.
     * 
     * @return El nombre de la seleccion visitante
     */
    public String getSeleccionVisitante(){
        return this.seleccionVisitante;
    }
    
    /**
     * Obtiene la fecha del partido.
     * 
     * @return La fecha y hora del partido
     */
    public Date getFecha(){
        return this.fecha;
    }
    
    /**
     * Obtiene el nombre del estadio.
     * 
     * @return El nombre del estadio donde se juega
     */
    public String getEstadio(){
        return this.estadio;
    }
    
    /**
     * Obtiene la ciudad donde se ubica el estadio.
     * 
     * @return El nombre de la ciudad
     */
    public String getCiudad(){
        return this.ciudad;
    }
    
    /**
     * Obtiene la capacidad del estadio.
     * 
     * @return El numero de personas que caben en el estadio
     */
    public int getCapacidad(){
        return this.capacidad;
    }
    
    /**
     * Obtiene la fase del torneo.
     * 
     * @return La fase en que se encuentra el partido
     */
    public String getFase(){
        return this.fase;
    }
    
    /**
     * Obtiene las entradas disponibles en la zona general.
     * 
     * @return El numero de entradas disponibles en la zona general
     */
    public int getEntradasGeneralDisponibles(){
        return this.entradasGeneralDisponibles;
    }
    
    /**
     * Obtiene las entradas disponibles en la zona preferencial.
     * 
     * @return El numero de entradas disponibles en la zona preferencial
     */
    public int getEntradasPrefencialDisponibles(){
        return this.entradasPrefencialDisponibles;
    }
    
    /**
     * Obtiene las entradas disponibles en la zona VIP.
     * 
     * @return El numero de entradas disponibles en la zona VIP
     */
    public int getEntradasVipDisponibles(){
        return this.entradasVipDisponibles;
    }
    
    /**
     * Obtiene el precio unitario de la zona general.
     * 
     * @return El precio de una entrada en la zona general
     */
    public double getPrecioGeneral(){
        return this.precioGeneral;
    }
    
    /**
     * Obtiene el precio unitario de la zona preferencial.
     * 
     * @return El precio de una entrada en la zona preferencial
     */
    public double getPrecioPreferencial(){
        return this.precioPrefencial;
    }
    
    /**
     * Obtiene el precio unitario de la zona VIP.
     * 
     * @return El precio de una entrada en la zona VIP
     */
    public double getPrecioVip(){
        return this.precioVip;
    }
    
    /**
     * Establece el codigo del partido.
     * 
     * @param codigo El codigo a asignar
     */
    public void setCodigo(String codigo){
        this.codigo=codigo;
    }
    
    /**
     * Establece la seleccion local del partido.
     * 
     * @param seleccionLocal El nombre de la seleccion a asignar
     */
    public void setSeleccionLocal(String seleccionLocal){
        this.seleccionLocal=seleccionLocal;
    }
    
    /**
     * Establece la seleccion visitante del partido.
     * 
     * @param seleccionVisitante El nombre de la seleccion a asignar
     */
    public void setSeleccionVisitante(String seleccionVisitante){
        this.seleccionVisitante=seleccionVisitante;
    }
    
    /**
     * Establece la fecha del partido.
     * 
     * @param fecha La fecha y hora a asignar
     */
    public void setFecha(Date fecha){
        this.fecha=fecha;
    }
    
    /**
     * Establece el nombre del estadio.
     * 
     * @param estadio El nombre del estadio a asignar
     */
    public void setEstadio(String estadio){
        this.estadio=estadio;
    }
    
    /**
     * Establece la ciudad del estadio.
     * 
     * @param ciudad El nombre de la ciudad a asignar
     */
    public void setCiudad(String ciudad){
        this.ciudad=ciudad;
    }
    
    /**
     * Establece la capacidad del estadio.
     * 
     * @param capacidad La capacidad a asignar
     */
    public void setCapacidad(int capacidad){
        this.capacidad=capacidad;
    }
    
    /**
     * Establece la fase del torneo.
     * 
     * @param fase La fase a asignar
     */
    public void setFase(String fase){
        this.fase=fase;
    }
    
    /**
     * Establece las entradas disponibles en la zona general.
     * 
     * @param entradasGeneralDisponibles El numero de entradas a asignar
     */
    public void setEntradasGeneralDisponibles(int entradasGeneralDisponibles){
        this.entradasGeneralDisponibles=entradasGeneralDisponibles;
    }
    
    /**
     * Establece las entradas disponibles en la zona preferencial.
     * 
     * @param entradasPrefencialDisponibles El numero de entradas a asignar
     */
    public void setEntradasPrefencialDisponibles(int entradasPrefencialDisponibles){
        this.entradasPrefencialDisponibles=entradasPrefencialDisponibles;
    }
    
    /**
     * Establece las entradas disponibles en la zona VIP.
     * 
     * @param entradasVipDisponibles El numero de entradas a asignar
     */
    public void setEntradasVipDisponibles(int entradasVipDisponibles){
        this.entradasVipDisponibles=entradasVipDisponibles;
    }
    
    /**
     * Establece el precio unitario de la zona general.
     * 
     * @param precioGeneral El precio a asignar
     */
    public void setPrecioGeneral(double precioGeneral){
        this.precioGeneral=precioGeneral;
    }
    
    /**
     * Establece el precio unitario de la zona preferencial.
     * 
     * @param precioPrefencial El precio a asignar
     */
    public void setPrecioPreferencial(double precioPrefencial){
        this.precioPrefencial=precioPrefencial;
    }
    
    /**
     * Establece el precio unitario de la zona VIP.
     * 
     * @param precioVip El precio a asignar
     */
    public void setPrecioVip(double precioVip){
        this.precioVip=precioVip;
    }

    /**
     * Verifica si hay suficiente stock de entradas para una zona dada.
     * 
     * Comprueba si la cantidad de entradas disponibles en la zona especificada
     * es mayor o igual a la cantidad solicitada.
     * 
     * @param zona La zona de entradas (GENERAL, PREFERENCIAL o VIP)
     * @param cantidad La cantidad de entradas a verificar
     * @return true si hay suficiente stock, false en caso contrario
     */
    public boolean hayStock(Zona zona, int cantidad){
        switch (zona) {
            case GENERAL:
                return this.entradasGeneralDisponibles>= cantidad;
            case PREFERENCIAL:
                return this.entradasPrefencialDisponibles >= cantidad;
            case VIP:
                return this.entradasVipDisponibles >= cantidad;    
            default:
                return false;
        }
    }

    /**
     * Descuenta entradas del stock disponible para una zona dada.
     * 
     * Resta la cantidad especificada del inventario de entradas para la zona indicada,
     * pero solo si hay suficiente stock disponible. Verifica el stock antes de realizar
     * el descuento para evitar inventario negativo.
     * 
     * @param zona La zona de entradas (GENERAL, PREFERENCIAL o VIP)
     * @param cantidad La cantidad de entradas a descontar
     */
    public void descontarStock(Zona zona, int cantidad){
        if (hayStock(zona, cantidad)){
            switch (zona) {
            case GENERAL:
                this.entradasGeneralDisponibles -= cantidad;
            case PREFERENCIAL:
                this.entradasPrefencialDisponibles -= cantidad;
            case VIP:
                this.entradasVipDisponibles -= cantidad;
                break; 
            }
        }
    }
}