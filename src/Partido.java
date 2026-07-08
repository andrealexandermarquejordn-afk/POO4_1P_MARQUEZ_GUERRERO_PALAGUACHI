import java.util.Date;
public class Partido {
    private String codigo;
    private String seleccionLocal;
    private String seleccionVisitante;
    private Date fecha;
    private String estadio;
    private String ciudad;
    private int capacidad;
    private String fase;
    private int entradasGeneralDisponibles;
    private int entradasPrefencialDisponibles;
    private int entradasVipDisponibles;
    private double precioGeneral;
    private double precioPrefencial;
    private double precioVip;

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
    //Metodos getters
    public String getCodigo(){
        return this.codigo;
    }
    public String getSeleccionLocal(){
        return this.seleccionLocal;
    }
    public String getSeleccionVisitante(){
        return this.seleccionVisitante;
    }
    public Date getFecha(){
        return this.fecha;
    }
    public String getEstadio(){
        return this.estadio;
    }
    public String getCiudad(){
        return this.ciudad;
    }
    public int getCapacidad(){
        return this.capacidad;
    }
    public String getFase(){
        return this.fase;
    }
    public int getEntradasGeneralDisponibles(){
        return this.entradasGeneralDisponibles;
    }
    public int getEntradasPrefencialDisponibles(){
        return this.entradasPrefencialDisponibles;
    }
    public int getEntradasVipDisponibles(){
        return this.entradasVipDisponibles;
    }
    public double getPrecioGeneral(){
        return this.precioGeneral;
    }
    public double getPrecioPreferencial(){
        return this.precioPrefencial;
    }
    public double getPrecioVip(){
        return this.precioVip;
    }
    //Metodos setters
    public void setCodigo(String codigo){
        this.codigo=codigo;
    }
    public void setSeleccionLocal(String seleccionLocal){
        this.seleccionLocal=seleccionLocal;
    }
    public void setSeleccionVisitante(String seleccionVisitante){
        this.seleccionVisitante=seleccionVisitante;
    }
    public void setFecha(Date fecha){
        this.fecha=fecha;
    }
    public void setEstadio(String estadio){
        this.estadio=estadio;
    }
    public void setCiudad(String ciudad){
        this.ciudad=ciudad;
    }
    public void setCapacidad(int capacidad){
        this.capacidad=capacidad;
    }
    public void setFase(String fase){
        this.fase=fase;
    }
    public void setEntradasGeneralDisponibles(int entradasGeneralDisponibles){
        this.entradasGeneralDisponibles=entradasGeneralDisponibles;
    }
    public void setEntradasPrefencialDisponibles(int entradasPrefencialDisponibles){
        this.entradasPrefencialDisponibles=entradasPrefencialDisponibles;
    }
    public void setEntradasVipDisponibles(int entradasVipDisponibles){
        this.entradasVipDisponibles=entradasVipDisponibles;
    }
    public void setPrecioGeneral(double precioGeneral){
        this.precioGeneral=precioGeneral;
    }
    public void setPrecioPreferencial(double precioPrefencial){
        this.precioPrefencial=precioPrefencial;
    }
    public void setPrecioVip(double precioVip){
        this.precioVip=precioVip;
    }

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