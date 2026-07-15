import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal que contiene la lógica de ejecución del sistema.
 * 
 * Implementa el punto de entrada de la aplicación y gestiona la interfaz de usuario
 * en línea de comandos. Coordina la carga de datos, la autenticación de usuarios,
 * y el enrutamiento a los menús correspondientes según el tipo de usuario (aficionado u organizador).
 * 
 * @author Sistema de Gestión de Venta de Entradas
 * @version 1.0
 * @since 2024
 */
public class Main {
    
    /**
     * Método principal que inicia la aplicación.
     * 
     * Inicializa el sistema, carga todos los datos persistentes (usuarios, partidos, kits, compras),
     * y presenta un bucle principal que gestiona la autenticación de usuarios y el enrutamiento
     * a los menús correspondientes. Cierra los recursos al finalizar.
     * 
     * @param args Argumentos de línea de comandos (no utilizado)
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();
        sistema.cargarUsuarios();
        sistema.cargarPartidos();
        sistema.cargarKits();
        sistema.cargarCompras();
        boolean ejecutando = true;



        while (ejecutando) {
            System.out.println("\n "+ "    SISTEMA DE VENTA - MUNDIAL 2026     ");
            ArrayList<Object> lista  = sistema.iniciarSesion();
            ejecutando=(boolean)lista.get(0);
            if (!ejecutando){
                if (lista.get(1) instanceof Aficionado) {
                    Aficionado aficionado = (Aficionado) lista.get(1);
                    menuAficionado(scanner, sistema, aficionado);
                } else if (lista.get(1) instanceof Organizador) {
                    Organizador organizador = (Organizador) lista.get(1);
                    menuOrganizador(scanner, sistema, organizador);
                }
            }
        }
        scanner.close();
    }

    /**
     * Muestra el menú interactivo de aficionado y gestiona sus operaciones.
     * 
     * Proporciona opciones para consultar partidos, comprar entradas individuales,
     * comprar kits de entradas, consultar historial de compras y cerrar sesión.
     * Valida las entradas del usuario y ejecuta las operaciones solicitadas.
     * 
     * @param scanner Objeto Scanner para leer entrada del usuario
     * @param sistema Instancia del sistema con la lógica de negocio
     * @param aficionado Usuario aficionado autenticado
     */
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

    /**
     * Muestra el menú interactivo de organizador y gestiona sus operaciones.
     * 
     * Proporciona opciones para consultar entradas vendidas, generar reportes de ventas
     * y cerrar sesión. Ejecuta las operaciones administrativas solicitadas.
     * 
     * @param scanner Objeto Scanner para leer entrada del usuario
     * @param sistema Instancia del sistema con la lógica de negocio
     * @param organizador Usuario organizador autenticado
     */
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