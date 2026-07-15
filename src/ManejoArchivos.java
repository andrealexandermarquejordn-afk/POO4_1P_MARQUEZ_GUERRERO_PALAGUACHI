import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Clase utilidad para la lectura y escritura de archivos de texto.
 * 
 * Proporciona métodos estáticos para leer archivos completos en listas de strings
 * y escribir contenido en archivos con soporte para codificación UTF-8.
 * Maneja adecuadamente los recursos de entrada/salida con bloques try-finally.
 * 
 * @author Sistema de Persistencia de Datos
 * @version 1.0
 * @since 2024
 */
public class ManejoArchivos {
    
    /**
     * Lee todas las líneas de un archivo de texto.
     * 
     * Abre el archivo especificado con codificación UTF-8 y lee línea por línea,
     * almacenando cada una en un ArrayList de strings. El archivo se cierra automáticamente
     * incluso si ocurre una excepción durante la lectura.
     * 
     * @param nombrearchivo Ruta del archivo a leer
     * @return ArrayList contiendo todas las líneas del archivo, o vacío si hay error
     */
    public static ArrayList<String> LeeFichero(String nombrearchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(nombrearchivo);
            fr = new FileReader(archivo,StandardCharsets.UTF_8);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lineas;

    }

    /**
     * Escribe una línea de texto en un archivo.
     * 
     * Añade una línea al final del archivo especificado en modo append.
     * Si el archivo no existe, lo crea. La línea se escribe con salto de línea al final.
     * Los recursos de escritura se cierran automáticamente.
     * 
     * @param nombreArchivo Ruta del archivo donde escribir
     * @param linea Línea de texto a escribir
     */
    public static void EscribirArchivo(String nombreArchivo, String linea) {

        FileWriter fichero = null;
        BufferedWriter bw = null;
      
        try {
            fichero = new FileWriter(nombreArchivo,true);
            bw = new BufferedWriter(fichero);
            bw.write(linea+"\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    //fichero.close();
                    bw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}