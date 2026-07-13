public class Aficionado extends Usuario{
    private String celular;
    private String paisFavorito;
    public Aficionado(String codigoUnico, String cedula, String nombres, String apellidos, String username,
            String password, String correo, char rol, String celular, String paisFavorito){
        super(codigoUnico, cedula, nombres, apellidos, username, password, correo, rol);
        this.celular = celular;
        this.paisFavorito = paisFavorito;
    }
    @Override
    public void consultarEntrada(Sistema sistema){
        System.out.println("Mis Compras (Aficionado: " + getNombres() + ")");
    }
    public String getCelular(){
        return celular; 
    }
    public String getPaisFavorito(){
        return paisFavorito; 
    }
}
