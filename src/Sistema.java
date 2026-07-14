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

    // Muestra en consola todos los partidos disponibles.
    public void consultarPartidos() {
        System.out.println("Partidos encontrados:\n");
        int contador = 1;
        for (Partido p : this.partidos) {
            System.out.println(contador + ". Código: " + p.getCodigo());
            System.out.println("   Partido: " + p.getSeleccionLocal() + " vs " + p.getSeleccionVisitante());
            System.out.println("   Fecha: " + formatoFecha.format(p.getFecha()));
            System.out.println("   Estadio: " + p.getEstadio());
            System.out.println("   Ciudad: " + p.getCiudad());
            System.out.println("   Fase: " + p.getFase());
            System.out.println();
            System.out.println("   Zonas disponibles:");
            System.out.println("   - GENERAL       | Disponibles: " + p.getEntradasGeneralDisponibles()
                    + " | Precio: $" + String.format("%.2f", p.getPrecioGeneral()));
            System.out.println("   - PREFERENCIAL  | Disponibles: " + p.getEntradasPrefencialDisponibles()
                    + " | Precio: $" + String.format("%.2f", p.getPrecioPreferencial()));
            System.out.println("   - VIP           | Disponibles: " + p.getEntradasVipDisponibles()
                    + " | Precio: $" + String.format("%.2f", p.getPrecioVip()));
            System.out.println("\n--------------------------------------------------");
            contador++;
        }
    }
 
     public void consultarKits() {
        System.out.println("===== KITS DISPONIBLES =====\n");
        int contador = 1;
        for (Kit k : this.kits) {
            System.out.println(contador + ". " + k.getNombre() + " (Código: " + k.getCodigo() + ")");
            System.out.println("Incluye:");
            for (String codigoPartido : k.getCodigosPartidosIncluidos()) {
                Partido p = buscarPartido(codigoPartido);
                if (p != null) {
                    System.out.println("- " + p.getSeleccionLocal() + " vs " + p.getSeleccionVisitante());
                }
            }
            System.out.println();
            System.out.println("Precio: $" + String.format("%.2f", k.getPrecio()));
            System.out.println("Disponibles: " + k.getDisponibles());
            System.out.println();
            contador++;
        }
    }

    public void notificar(Aficionado aficionado, Compra compra, Zona zona) {
        Partido partido = buscarPartido(compra.getCodigoReferencia());

        String cuerpo = "Estimado/a " + aficionado.getNombres() + " " + aficionado.getApellidos() + ",\n"
                + "Su compra ha sido registrada exitosamente con el código " + compra.getCodigoCompra()
                + " el día " + compra.getFechaCompra() + ".\n";
        if (partido != null) {
            cuerpo += "Partido: " + partido.getSeleccionLocal() + " vs " + partido.getSeleccionVisitante() + "\n";
            cuerpo += "Código del partido: " + partido.getCodigo() + "\n";
        }
        cuerpo += "Zona: " + zona + "\n";
        cuerpo += "Cantidad: " + compra.getCantidad() + "\n";
        cuerpo += "Valor pagado: $" + String.format("%.2f", compra.getValorPagado()) + "\n";
        cuerpo += "Gracias por adquirir sus entradas para el Mundial. Recuerde conservar el código de compra para futuras consultas.";

        CorreoElectronico.enviarCorreo(aficionado.getCorreo(), "Compra de entrada realizada", cuerpo);
    }

    public void notificar(Aficionado aficionado, Compra compra, Kit kit) {
        String cuerpo = "Estimado/a " + aficionado.getNombres() + " " + aficionado.getApellidos() + ",\n"
                + "Su compra de paquete ha sido registrada exitosamente con el código " + compra.getCodigoCompra()
                + " el día " + compra.getFechaCompra() + ".\n"
                + "Detalle del Kit Adquirido: " + kit.getNombre() + " (" + kit.getDescripcion() + ")\n"
                + "Cantidad de Kits: " + compra.getCantidad() + "\n"
                + "Valor pagado: $" + String.format("%.2f", compra.getValorPagado()) + "\n"
                + "Gracias por adquirir sus paquetes oficiales para el Mundial. ¡Disfrute el evento!";

        CorreoElectronico.enviarCorreo(aficionado.getCorreo(), "Compra de Kit de Entradas realizada", cuerpo);
    }

    public void notificar(Organizador organizador, ReporteVenta reporte) {
        String cuerpo = "Estimado/a " + organizador.getNombres() + " " + organizador.getApellidos() + ",\n"
                + "Se ha generado el reporte de compras del sistema.\n"
                + "Fecha de generación del reporte: " + reporte.getFechaReporte() + "\n"
                + "Total de compras registradas: " + reporte.getTotalCompras() + "\n"
                + "Total de compras de entradas individuales: " + reporte.getTotalEntrada() + "\n"
                + "Total de compras de kits: " + reporte.getTotalKit() + "\n"
                + "Monto total recaudado: $" + String.format("%.2f", reporte.getMontoTotal());

        CorreoElectronico.enviarCorreo(organizador.getCorreo(), "Reporte de compras registradas", cuerpo);
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
            TipoUsuario rol = TipoUsuario.valueOf(datosUser[7]);    

            if (rol == TipoUsuario.A) {
                for (int j = 1; j < lineasAficionados.size(); j++) {
                    String[] datosAficionado = lineasAficionados.get(j).split("\\|");
                    if (datosAficionado[0].equals(codigo)) {
                        usuarios.add(new Aficionado(codigo, cedula, nombres, apellidos, username, password, correo, rol, datosAficionado[4], datosAficionado[5]));
                        break;
                    }
                }
            } else if (rol == TipoUsuario.O) {
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

    public ReporteVenta generarReporte() {
        int totalEntrada = 0;
        int totalKit = 0;
        double montoTotal = 0.0;
        
        for (Compra c : this.compras) {
            if (c.getTipo() == TipoCompra.ENTRADA) {
                totalEntrada++;
            } else if (c.getTipo() == TipoCompra.KIT) {
                totalKit++;
            }
            
            montoTotal += c.getValorPagado();
        }
        
        return new ReporteVenta(this.compras.size(), totalEntrada, totalKit, montoTotal, new Date());
    }


    public void cargarPartidos() {
        ArrayList<String> lineas = ManejoArchivos.LeeFichero("partidos.txt");
        for (int i = 1; i < lineas.size(); i++) {
            String[] datos = lineas.get(i).split("\\|");
            try {
                Date fecha = formatoFecha.parse(datos[3]);
                String fase = datos[10];

                double pGeneral;
                double pPreferencial;
                double pVip;

                if (fase.contains("Fase de grupos")) {
                    pGeneral = 45.00;
                    pPreferencial = 85.00;
                    pVip = 150.00;
                } else {
                    pGeneral = 60.00;
                    pPreferencial = 100.00;
                    pVip = 180.00;
                }

                partidos.add(new Partido(datos[0], datos[1], datos[2], fecha, datos[4], datos[5], Integer.parseInt(datos[6]), fase,
                Integer.parseInt(datos[7]), Integer.parseInt(datos[8]), Integer.parseInt(datos[9]), pGeneral, pPreferencial, pVip));
            } catch (Exception e) {
                System.out.println("Error al convertir la fecha del partido: " + e.getMessage());
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
            kits.add(new Kit(datos[0], datos[1], datos[2], codigosPartidosIncluidos, Double.parseDouble(datos[4]), Integer.parseInt(datos[5])));
        }
}

    public void cargarCompras() {
        ArrayList<String> lineas = ManejoArchivos.LeeFichero("compras.txt");
        for (int i = 0; i < lineas.size(); i++) {
            String[] datos = lineas.get(i).split("\\|");
            try {
                TipoCompra tipo = TipoCompra.valueOf(datos[1]);
                Date fecha = formatoFecha.parse(datos[3]);
                int cantidad = Integer.parseInt(datos[4]);
                double valorPagado = Double.parseDouble(datos[5]);
                Compra compra = new Compra(datos[0], tipo, datos[2], fecha, cantidad, valorPagado, datos[6]);
                compras.add(compra);
            } catch (Exception e) {
                System.out.println("Error al leer los datos de la compra: " + e.getMessage());
            }
        }
    }

    public void registrarCompra(Compra compra) {
        if (compra != null) {
            this.compras.add(compra);
            String lineaArchivo = compra.getCodigoCompra() + "|" + compra.getTipo() + "|" + compra.getCodigoReferencia() + 
                "|" + formatoFecha.format(compra.getFechaCompra()) + "|" + compra.getCantidad() +  
                "|" + compra.getValorPagado() + "|" + compra.getCodigoAficionado();
            ManejoArchivos.EscribirArchivo("compras.txt", lineaArchivo);
        }
    }

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

public void comprar(Partido partido, Aficionado aficionado, Zona zona, int cantidad) {
        if (partido.hayStock(zona, cantidad)) {
            partido.descontarStock(zona, cantidad);
            double precioUnitario = 0;
            switch(zona) {
                case GENERAL: 
                    precioUnitario = partido.getPrecioGeneral(); 
                    break;
                case PREFERENCIAL: 
                    precioUnitario = partido.getPrecioPreferencial(); 
                    break;
                case VIP: 
                    precioUnitario = partido.getPrecioVip(); 
                    break;
            }
            double valorTotal = precioUnitario * cantidad;
            Compra nuevaCompra = new Compra(TipoCompra.ENTRADA, partido.getCodigo(), cantidad, valorTotal, aficionado.getCodigoUnico());
            registrarCompra(nuevaCompra);
            notificar(aficionado, nuevaCompra, zona);
        } else {
            System.out.println("Error: No hay stock suficiente para la zona seleccionada.");
        }
    }

    public void comprar(Kit kit, Aficionado aficionado, int cantidad) {
        if (kit.getDisponibles() >= cantidad) {
            kit.setDisponibles(kit.getDisponibles() - cantidad);
            double valorTotal = kit.getPrecio() * cantidad;
            Compra nuevaCompra = new Compra(TipoCompra.KIT, kit.getCodigo(), cantidad, valorTotal, aficionado.getCodigoUnico());
            registrarCompra(nuevaCompra);
            notificar(aficionado, nuevaCompra, kit);
        } else {
            System.out.println("Error: No hay suficientes kits disponibles.");
        }
    }

    public Usuario autenticarUsuario(String username, String password) {
        for (Usuario u : this.usuarios) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null; 
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
}