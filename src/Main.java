import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();
        sistema.cargarUsuarios();
        sistema.cargarPartidos();
        sistema.cargarKits();
        sistema.cargarCompras();
        boolean ejecutando = true;



        while (ejecutando) {
            System.out.println("    SISTEMA DE VENTA - MUNDIAL 2026     ");
            System.out.print("Ingrese su usuario (o 'salir' para apagar): ");
            String username = scanner.nextLine();
            
            if (username.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del sistema.");
                ejecutando = false;
                break;
            }

            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();
            Usuario usuarioActual = sistema.autenticarUsuario(username, password);

            if (usuarioActual != null) {
                System.out.println("Usuario autenticado correctamente.");

                boolean identidadConfirmada = verificarIdentidad(scanner, usuarioActual);

                if (!identidadConfirmada) {
                    System.out.println("Verificación fallida.");
                    System.out.println("Por motivos de seguridad se cerrará la sesión.");
                    System.out.println("Saliendo del sistema..");
                    ejecutando = false;
                    break;
                }

                System.out.println("Identidad confirmada.\n");

                if (usuarioActual instanceof Aficionado) {
                    Aficionado aficionado = (Aficionado) usuarioActual;
                    menuAficionado(scanner, sistema, aficionado);
                } else if (usuarioActual instanceof Organizador) {
                    Organizador organizador = (Organizador) usuarioActual;
                    menuOrganizador(scanner, sistema, organizador);
                }
            } else {
                System.out.println("\n Usuario o contraseña incorrectos. Intente de nuevo.\n");
            }
        }
        scanner.close();
    }

    private static boolean verificarIdentidad(Scanner scanner, Usuario usuario) {
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

    // Menu aficionado
    private static void menuAficionado(Scanner scanner, Sistema sistema, Aficionado aficionado) {
        boolean enMenu = true;
        while (enMenu) {
            System.out.println("\n--- MENÚ AFICIONADO ---");
            System.out.println("1. Consultar Partidos");
            System.out.println("2. Comprar Entrada ");
            System.out.println("3. Comprar Kit de Entradas");
            System.out.println("4. Consultar Mis Compras");
            System.out.println("5. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    sistema.consultarPartidos();
                    break;

                case "2":
                    System.out.print("Ingrese el código del partido: ");
                    String codPartido = scanner.nextLine();
                    Partido partido = sistema.buscarPartido(codPartido);
                    
                    if (partido != null) {
                        System.out.println("Zonas disponibles: 1. GENERAL | 2. PREFERENCIAL | 3. VIP");
                        System.out.print("Seleccione la zona (1-3): ");
                        String opcZona = scanner.nextLine();
                        
                        Zona zonaSeleccionada = null;
                        if (opcZona.equals("1")) zonaSeleccionada = Zona.GENERAL;
                        else if (opcZona.equals("2")) zonaSeleccionada = Zona.PREFERENCIAL;
                        else if (opcZona.equals("3")) zonaSeleccionada = Zona.VIP;

                        if (zonaSeleccionada != null) {
                            System.out.print("Ingrese la cantidad de entradas: ");
                            try {
                                int cantidad = Integer.parseInt(scanner.nextLine());
                                
                                if (partido.hayStock(zonaSeleccionada, cantidad)) {
                                    
                                    double precioUnitario = 0;
                                    if (zonaSeleccionada == Zona.GENERAL) precioUnitario = partido.getPrecioGeneral();
                                    else if (zonaSeleccionada == Zona.PREFERENCIAL) precioUnitario = partido.getPrecioPreferencial();
                                    else if (zonaSeleccionada == Zona.VIP) precioUnitario = partido.getPrecioVip();
                                    
                                    double totalPagar = precioUnitario * cantidad;
                                    System.out.println("\nTotal a pagar: $" + String.format("%.2f", totalPagar));
                                    
                                    System.out.print("Ingrese número de tarjeta de crédito/débito: ");
                                    String tarjeta = scanner.nextLine();
                                    
                                    String ultimosDigitos;
                                    if (tarjeta.length() >= 4) {
                                        ultimosDigitos = tarjeta.substring(tarjeta.length() - 4);
                                    } else {
                                        ultimosDigitos = tarjeta;
                                    }
                                    System.out.println("Procesando pago con tarjeta terminada en " + ultimosDigitos);
                                    System.out.println("¡Pago exitoso!\n");
                                    
                                    sistema.comprar(partido, aficionado, zonaSeleccionada, cantidad);
                                    System.out.println("Su compra se generó exitosamente y se envio el comprobante a su correo.");
                                    
                                } else {
                                    System.out.println("Lo sentimos, no hay stock suficiente para esa zona.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Cantidad inválida.");
                            }
                        } else {
                            System.out.println("Zona no válida.");
                        }
                    } else {
                        System.out.println("Partido no encontrado.");
                    }
                    break;

                case "3":
                    sistema.consultarKits();
                    System.out.print("Ingrese el código del Kit: ");
                    String codKit = scanner.nextLine();
                    Kit kit = sistema.buscarKit(codKit);
                    
                    if (kit != null) {
                        System.out.print("Ingrese la cantidad de kits a comprar: ");
                        try {
                            int cantidad = Integer.parseInt(scanner.nextLine());
                            
                            if (kit.getDisponibles() >= cantidad) {
                                
                                double totalKit = kit.getPrecio() * cantidad;
                                System.out.println("\nTotal a pagar: $" + String.format("%.2f", totalKit));
                                
                                System.out.print("Ingrese número de tarjeta de crédito/débito: ");
                                String tarjeta = scanner.nextLine();
                                
                                String ultimosDigitos;
                                    if (tarjeta.length() >= 4) {
                                        ultimosDigitos = tarjeta.substring(tarjeta.length() - 4);
                                    } else {
                                        ultimosDigitos = tarjeta;
                                    } 
                                    System.out.println("Procesando pago con tarjeta terminada en " + ultimosDigitos);
                                    System.out.println("¡Pago exitoso!\n");
                                
                                sistema.comprar(kit, aficionado, cantidad);
                                System.out.println("Su paquete de kits se generó exitosamente y se envio el comprobante a su correo.");
                                
                            } else {
                                System.out.println("Lo sentimos, no hay suficientes kits disponibles.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Cantidad inválida.");
                        }
                    } else {
                        System.out.println("Kit no encontrado.");
                    }
                    break;

                case "4":
                    aficionado.consultarEntrada(sistema);
                    break;

                case "5":
                    System.out.println("Cerrando sesión...\n");
                    enMenu = false;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Menu organizador
    private static void menuOrganizador(Scanner scanner, Sistema sistema, Organizador organizador) {
        boolean enMenu = true;
        while (enMenu) {
            System.out.println("\n--- MENÚ ORGANIZADOR ---");
            System.out.println("1. Consultar Entradas");
            System.out.println("2. Generar Reporte de Ventas");
            System.out.println("3. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    organizador.consultarEntrada(sistema);
                    break;
                case "2":
                    organizador.generarReporteVentas(sistema);
                    break;
                case "3":
                    System.out.println("Cerrando sesión...\n");
                    enMenu = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}