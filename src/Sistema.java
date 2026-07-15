import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase central que gestiona la logica del sistema de venta de entradas.
 * 
 * Coordina todas las operaciones del sistema incluyendo autenticacion de usuarios,
 * consulta de partidos y kits, procesamiento de compras, generacion de reportes,
 * y envio de notificaciones. Gestiona la persistencia de datos mediante archivos
 * y mantiene las colecciones de usuarios, partidos, kits y compras en memoria.
 * 
 * @author Sistema de Gestion de Entradas
 * @version 1.0
 * @since 2024
 */
public class Sistema {
    
    /** Lista de todos los usuarios del sistema */
    private List<Usuario> usuarios;
    
    /** Lista de todos los partidos del torneo */
    private List<Partido> partidos;
    
    /** Lista de todos los kits de entradas disponibles */
    private List<Kit> kits;
    
    /** Lista de todas las compras realizadas */
    private List<Compra> compras;
    
    /** Formato utilizado para parsear y formatear fechas */
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    
    /** Scanner para leer entrada del usuario desde consola */
    Scanner scanner = new Scanner(System.in);

    /**
     * Construye un nuevo Sistema inicializando todas las colecciones.
     */
    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.kits = new ArrayList<>();
        this.compras = new ArrayList<>();
    }

    /**
     * Muestra en consola todos los partidos disponibles con detalles.
     * 
     * Presenta codigo, equipos, fecha, ubicacion, fase y disponibilidad de entradas.
     */
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
 
    /**
     * Muestra en consola todos los kits disponibles con detalles.
     * 
     * Presenta nombre, codigo, partidos incluidos, precio y disponibilidad.
     */
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

    /**
     * Envia notificacion por correo sobre compra de entrada.
     * 
     * @param aficionado El aficionado que realizo la compra
     * @param compra La compra registrada
     * @param zona La zona de entradas comprada
     */
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

    /**
     * Envia notificacion por correo sobre compra de kit.
     * 
     * @param aficionado El aficionado que realizo la compra
     * @param compra La compra de kit registrada
     * @param kit El kit que fue comprado
     */
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

    /**
     * Envia notificacion por correo sobre reporte de ventas.
     * 
     * @param organizador El organizador que recibe el reporte
     * @param reporte El reporte de ventas generado
     */
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

    /**
     * Carga todos los usuarios desde los archivos de datos.
     * 
     * Lee usuarios, aficionados y organizadores desde archivos de texto
     * y construye los objetos correspondientes para agregarlos al sistema.
     */
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

    /**
     * Genera un reporte consolidado de todas las ventas del sistema.
     * 
     * @return ReporteVenta con total de compras, distribucion por tipo y monto
     */
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


    /**
     * Carga todos los partidos desde el archivo de datos.
     * 
     * Lee partidos del archivo y establece precios automaticos segun la fase.
     */
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

    /**
     * Carga todos los kits desde el archivo de datos.
     * 
     * Lee kits y sus partidos asociados desde el archivo de datos.
     */
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

    /**
     * Carga todas las compras desde el archivo de datos.
     * 
     * Lee compras previamente registradas y las carga en memoria.
     */
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

    /**
     * Registra una nueva compra en el sistema y la persiste en archivo.
     * 
     * @param compra La compra a registrar
     */
    public void registrarCompra(Compra compra) {
        if (compra != null) {
            this.compras.add(compra);
            String lineaArchivo = compra.getCodigoCompra() + "|" + compra.getTipo() + "|" + compra.getCodigoReferencia() + 
                "|" + formatoFecha.format(compra.getFechaCompra()) + "|" + compra.getCantidad() +  
                "|" + compra.getValorPagado() + "|" + compra.getCodigoAficionado();
            ManejoArchivos.EscribirArchivo("compras.txt", lineaArchivo);
        }
    }

    /**
     * Busca un partido por su codigo.
     * 
     * @param codigo El codigo del partido a buscar
     * @return El partido encontrado o null si no existe
     */
    public Partido buscarPartido(String codigo) {
        for (Partido p : partidos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) return p;
        }
        return null;
    }

    /**
     * Busca un kit por su codigo.
     * 
     * @param codigo El codigo del kit a buscar
     * @return El kit encontrado o null si no existe
     */
    public Kit buscarKit(String codigo) {
        for (Kit k : kits) {
            if (k.getCodigo().equalsIgnoreCase(codigo)) return k;
        }
        return null;
    }

    /**
     * Procesa la compra de entradas individuales de un partido.
     * 
     * Verifica stock, descuenta entradas, crea la compra y notifica al aficionado.
     * 
     * @param partido El partido para el cual se compran entradas
     * @param aficionado El aficionado que realiza la compra
     * @param zona La zona de entradas a comprar
     * @param cantidad La cantidad de entradas a comprar
     */
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

    /**
     * Procesa la compra de kits de entradas.
     * 
     * Verifica disponibilidad, descuenta kits, crea la compra y notifica al aficionado.
     * 
     * @param kit El kit a comprar
     * @param aficionado El aficionado que realiza la compra
     * @param cantidad La cantidad de kits a comprar
     */
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

    /**
     * Autentica un usuario por username y password.
     * 
     * @param username El nombre de usuario
     * @param password La contrasena del usuario
     * @return El usuario autenticado o null si las credenciales son invalidas
     */
    public Usuario autenticarUsuario(String username, String password) {
        for (Usuario u : this.usuarios) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null; 
    }

    /**
     * Gestiona el inicio de sesion de un usuario.
     * 
     * Solicita credenciales, autentica y verifica la identidad del usuario.
     * 
     * @return ArrayList con [booleano de continuacion, usuario autenticado]
     */
    public ArrayList<Object> iniciarSesion(){
        ArrayList<Object> lista = new ArrayList<>();
        boolean iniciado=true;
        lista.add(iniciado);
        System.out.print("Ingrese su usuario (o 'salir' para apagar): ");
            String username = scanner.nextLine();
            
            if (username.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del sistema.");
                lista.set(0,false);
                lista.add(null);
                return lista;
            }

            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();
            Usuario usuarioActual = autenticarUsuario(username, password);
            lista.add(usuarioActual);

            if (usuarioActual != null) {
                System.out.println("Usuario autenticado correctamente.");
                boolean identidadConfirmada = verificarIdentidad(scanner, usuarioActual);

                if (!identidadConfirmada) {
                    System.out.println("Verificación fallida.");
                    System.out.println("Por motivos de seguridad se cerrará la sesión.");
                    System.out.println("Saliendo del sistema..");
                    return lista;
                }
                System.out.println("Identidad confirmada.\n");
                lista.set(0,false);
                return lista;
            } else {
                System.out.println("\n Usuario o contraseña incorrectos. Intente de nuevo.\n");
                return lista;
            }
    }
    
    /**
     * Verifica la identidad del usuario autenticado.
     * 
     * Confirma informacion personal del usuario mediante una pregunta de seguridad.
     * 
     * @param scanner Scanner para leer entrada del usuario
     * @param usuario El usuario a verificar
     * @return true si la identidad es confirmada, false en caso contrario
     */
    public static boolean verificarIdentidad(Scanner scanner, Usuario usuario) {
        String respuesta;

        if (usuario instanceof Aficionado) {
            Aficionado aficionado = (Aficionado) usuario; 
            System.out.println("Rol detectado: AFICIONADO");
            System.out.println("Bienvenido/a, " + aficionado.getNombres() + " " + aficionado.getApellidos());
            System.out.println("Celular registrado: " + aficionado.getCelular());
            System.out.print("¿Este número de celular es correcto? (S/N): ");
            respuesta = scanner.nextLine();

        } else if (usuario instanceof Organizador) {
            Organizador organizador = (Organizador) usuario; 
            System.out.println("Rol detectado: ORGANIZADOR");
            System.out.println("Bienvenido/a, " + organizador.getNombres() + " " + organizador.getApellidos());
            System.out.println("Empresa asignada: " + organizador.getEmpresa());
            System.out.print("¿Esta empresa es correcta? (S/N): ");
            respuesta = scanner.nextLine();

        } else {
            return false;
        }

        return respuesta.equalsIgnoreCase("S");
    }
    
    /**
     * Establece la lista de usuarios del sistema.
     * 
     * @param usuarios La lista de usuarios a asignar
     */
    public void setUsuarios(List<Usuario> usuarios) { 
        this.usuarios = usuarios; 
    }
    
    /**
     * Establece la lista de partidos del sistema.
     * 
     * @param partidos La lista de partidos a asignar
     */
    public void setPartidos(List<Partido> partidos) { 
        this.partidos = partidos; 
    }
    
    /**
     * Establece la lista de kits del sistema.
     * 
     * @param kits La lista de kits a asignar
     */
    public void setKits(List<Kit> kits) { 
        this.kits = kits; 
    }
    
    /**
     * Establece la lista de compras del sistema.
     * 
     * @param compras La lista de compras a asignar
     */
    public void setCompras(List<Compra> compras) { 
        this.compras = compras; 
    }

    /**
     * Obtiene la lista de usuarios del sistema.
     * 
     * @return La lista de usuarios
     */
    public List<Usuario> getUsuarios() { 
        return usuarios; 
    }
    
    /**
     * Obtiene la lista de partidos del sistema.
     * 
     * @return La lista de partidos
     */
    public List<Partido> getPartidos() { 
        return partidos; 
    }
    
    /**
     * Obtiene la lista de kits del sistema.
     * 
     * @return La lista de kits
     */
    public List<Kit> getKits() { 
        return kits; 
    }
    
    /**
     * Obtiene la lista de compras del sistema.
     * 
     * @return La lista de compras
     */
    public List<Compra> getCompras() { 
        return compras; 
    }
}