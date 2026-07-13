import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Sistema {
    private List<Usuario> usuarios;
    private List<Partido> partidos;
    private List<Kit> kits;
    private List<Compra> compras;

    private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.kits = new ArrayList<>();
        this.compras = new ArrayList<>();
    }
    //Metodos getters
    public List<Usuario> getUsuarios() { 
        return usuarios; 
    }
    public List<Partido> getPartidos() { 
        return partidos; 
    }
    public List<Kit> getKits() { 
        return kits; 
    }
    public List<Compra> getCompras() { 
        return compras; 
    }
    //Metodos setters
    public void setUsuarios(List<Usuario> usuarios) { 
        this.usuarios = usuarios; 
    }
    public void setPartidos(List<Partido> partidos) { 
        this.partidos = partidos; 
    }
    public void setKits(List<Kit> kits) { 
        this.kits = kits; 
    }
    public void setCompras(List<Compra> compras) { 
        this.compras = compras; 
    }

    public void notificar(Aficionado aficionado, Compra compra) {
        System.out.println("De: correoSistema@mundial2026.com");
        System.out.println("Para: " + aficionado.getCorreo());
        System.out.println("Asunto: Compra de entrada realizada");
        System.out.println("Estimado/a " + aficionado.getNombres() + " " + aficionado.getApellidos() + ",");
        System.out.println("Su compra ha sido registrada exitosamente con el código " + compra.getCodigoCompra() + " el día " + compra.getFechaCompra() + ".");
        Partido partido = buscarPartido(compra.getCodigoReferencia());
        if (partido != null) {
            System.out.println("Partido: " + partido.getSeleccionLocal() + " vs " + partido.getSeleccionVisitante());
            System.out.println("Código del partido: " + partido.getCodigo());
        }
        
        System.out.println("Cantidad: " + compra.getCantidad());
        System.out.println("Valor pagado: $" + String.format("%.2f", compra.getValorPagado()));
        System.out.println("Gracias por adquirir sus entradas para el Mundial. Recuerde conservar el código de compra para futuras consultas.");
        System.out.println("------------------------------------------------");
    }
    public void notificar(Aficionado aficionado, Compra compra, Kit kit) {
        System.out.println("De: correoSistema@mundial2026.com");
        System.out.println("Para: " + aficionado.getCorreo());
        System.out.println("Asunto: Compra de Kit de Entradas realizada");
        System.out.println("Estimado/a " + aficionado.getNombres() + " " + aficionado.getApellidos() + ",");
        System.out.println("Su compra de paquete ha sido registrada exitosamente con el código " + compra.getCodigoCompra() + " el día " + compra.getFechaCompra() + ".");
        System.out.println("Detalle del Kit Adquirido: " + kit.getNombre() + " (" + kit.getDescripcion() + ")");
        System.out.println("Cantidad de Kits: " + compra.getCantidad());
        System.out.println("Valor pagado: $" + String.format("%.2f", compra.getValorPagado()));
        System.out.println("Gracias por adquirir sus paquetes oficiales para el Mundial. ¡Disfrute el evento!");
        System.out.println("--------------------------------------------");
    }

    public void notificar(Organizador organizador, ReporteVenta reporte) {
        System.out.println("De: correoSistema@mundial2026.com");
        System.out.println("Para: " + organizador.getCorreo());
        System.out.println("Asunto: Reporte de compras registradas");
        System.out.println("Estimado/a " + organizador.getNombres() + " " + organizador.getApellidos() + ",");
        System.out.println("Se ha generado el reporte de compras del sistema.");
        System.out.println("Fecha de generación del reporte: " + reporte.getFechaReporte());
        System.out.println("Total de compras registradas: " + reporte.getTotalCompras());
        System.out.println("Total de compras de entradas individuales: " + reporte.getTotalEntrada());
        System.out.println("Total de compras de kits: " + reporte.getTotalKit());
        System.out.println("Monto total recaudado: $" + String.format("%.2f", reporte.getMontoTotal()));
        System.out.println("Este reporte corresponde a las compras registradas en el archivo compras.txt.");
        System.out.println("-----------------------------------------------------");
    }

    public void cargarUsuarios() {
        ArrayList<String> lineasUsuarios = ManejoArchivos.LeeFichero("usuarios.txt");
        ArrayList<String> lineasAficionados = ManejoArchivos.LeeFichero("aficionados.txt");
        ArrayList<String> lineasOrganizadores = ManejoArchivos.LeeFichero("organizadores.txt");

        for (int i = 1; i < lineasUsuarios.size(); i++) {
            String[] datosUser = lineasUsuarios.get(i).split("\\|");
            String codigo = datosUser[0];
            String cedula = datosUser[1];
            String nombres = datosUser[2];
            String apellidos = datosUser[3];
            String username = datosUser[4];
            String password = datosUser[5];
            String correo = datosUser[6];
            char rol = datosUser[7].charAt(0);

            if (rol == 'A') {
                for (int j = 1; j < lineasAficionados.size(); j++) {
                    String[] datosAficionado = lineasAficionados.get(j).split("\\|");
                    if (datosAficionado[0].equals(codigo)) {
                        usuarios.add(new Aficionado(codigo, cedula, nombres, apellidos, username, password, correo, rol, datosAficionado[4], datosAficionado[5]));
                        break;
                    }
                }
            } else if (rol == 'O') {
                for (int j = 1; j < lineasOrganizadores.size(); j++) {
                    String[] datosOrganizador = lineasOrganizadores.get(j).split("\\|");
                    if (datosOrganizador[0].equals(codigo)) {
                        usuarios.add(new Organizador(codigo, cedula, nombres, apellidos, username, password, correo, rol, datosOrganizador[4], datosOrganizador[5]));
                        break;
                    }
                }
            }
        }
    }

    public void cargarPartidos() {
        ArrayList<String> lineas = ManejoArchivos.LeeFichero("partidos.txt");
        for (int i = 1; i < lineas.size(); i++) {
            String[] datos = lineas.get(i).split("\\|");
            try {
                Date fecha = formatoFecha.parse(datos[3]);
                double pGeneral = (datos[10].contains("Fase de grupos")) ? 45.00 : 60.00;
                double pPreferencial = (datos[10].contains("Fase de grupos")) ? 85.00 : 100.00;
                double pVip = (datos[10].contains("Fase de grupos")) ? 150.00 : 180.00;

                partidos.add(new Partido(datos[0], datos[1], datos[2], fecha, datos[4], datos[5], 
                                         Integer.parseInt(datos[6]), datos[10], Integer.parseInt(datos[7]), 
                                         Integer.parseInt(datos[8]), Integer.parseInt(datos[9]), pGeneral, pPreferencial, pVip));
            } catch (Exception e) {
                System.out.println("Error parseando fecha: " + e.getMessage());
            }
        }
    }

    public void cargarKits() {
        ArrayList<String> lineas = ManejoArchivos.LeeFichero("kits.txt");
        for (int i = 1; i < lineas.size(); i++) {
            String[] datos = lineas.get(i).split("\\|");
            String[] separarCodigos = datos[3].split(","); 
            ArrayList<String> codigosPartidosIncluidos = new ArrayList<>();
            for (String cp : separarCodigos) {
                codigosPartidosIncluidos.add(cp.trim());
            }
            kits.add(new Kit(
                    datos[0],
                    datos[1],
                    datos[2],
                    codigosPartidosIncluidos,
                    Double.parseDouble(datos[4]),
                    Integer.parseInt(datos[5])));
        }
}

    public void registrarCompra(Compra compra) {
        if (compra != null) {
            this.compras.add(compra);
            String lineaArchivo = compra.getCodigoCompra() + "|" + compra.getTipo() + "|" + compra.getCodigoReferencia() + "|" +
                                  formatoFecha.format(compra.getFechaCompra()) + "|" + compra.getCantidad() + "|" +
                                  compra.getValorPagado() + "|" + compra.getCodigoAficionado();
            ManejoArchivos.EscribirArchivo("compras.txt", lineaArchivo);
        }
    }

    // Métodos de búsqueda requeridos
    public Partido buscarPartido(String codigo) {
        for (Partido p : partidos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) return p;
        }
        return null;
    }

    public Kit buscarKit(String codigo) {
        for (Kit k : kits) {
            if (k.getCodigo().equalsIgnoreCase(codigo)) return k;
        }
        return null;
    }

}