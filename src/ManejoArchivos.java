import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ManejoArchivos {

    public static ArrayList<String> LeeFichero(String nombrearchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File(nombrearchivo);
            if (archivo.exists()) { 
                fr = new FileReader(archivo, StandardCharsets.UTF_8);
                br = new BufferedReader(fr);

                String linea;
                while ((linea = br.readLine()) != null) {
                    lineas.add(linea);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lineas;
    }

    public static void EscribirArchivo(String nombreArchivo, String linea) {
        FileWriter fichero = null;
        BufferedWriter bw = null;
      
        try {
            fichero = new FileWriter(nombreArchivo, true);
            bw = new BufferedWriter(fichero);
            bw.write(linea + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bw) {
                    bw.close();
                }
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void EscribirArchivo(String nombreArchivo, ArrayList<String> lineas) {
        FileWriter fichero = null;
        BufferedWriter bw = null;
      
        try {
            // Sin el parámetro 'true', el archivo se sobrescribe desde cero
            fichero = new FileWriter(nombreArchivo, false);
            bw = new BufferedWriter(fichero);
            
            for (String linea : lineas) {
                bw.write(linea + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bw) {
                    bw.close();
                }
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}