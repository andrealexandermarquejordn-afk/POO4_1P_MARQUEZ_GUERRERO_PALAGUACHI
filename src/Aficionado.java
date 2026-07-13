public class Aficionado extends Usuario{
    private String celular;
    private String paisFavorito;
    public Aficionado(String codigoUnico, String cedula, String nombres, String apellidos, String username, String password, String correo, TipoUsuario rol, String celular, String paisFavorito){
        super(codigoUnico, cedula, nombres, apellidos, username, password, correo, rol);
        this.celular = celular;
        this.paisFavorito = paisFavorito;
    }


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

    
    //Metodos getters
    public String getCelular(){
        return celular; 
    }
    public String getPaisFavorito(){
        return paisFavorito; 
    }

    //Metodos setters
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public void setPaisFavorito(String paisFavorito) {
        this.paisFavorito = paisFavorito;
    }
}
