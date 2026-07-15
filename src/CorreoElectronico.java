import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Clase utilidad para el envío de correos electrónicos.
 * 
 * Proporciona funcionalidad para enviar correos electrónicos a través del servicio SMTP de Gmail.
 * Utiliza autenticación por contraseña de aplicación para garantizar la seguridad.
 * 
 * @author Sistema de Notificaciones
 * @version 1.0
 * @since 2024
 */
public class CorreoElectronico {

    /** Correo electrónico del remitente del sistema */
    private static final String MI_CORREO = "mundialprincipal2026@gmail.com";
    
    /** Clave de aplicación de Gmail para autenticación */
    private static final String MI_CLAVE_APP = "aajp msxo ahld pstz";

    /**
     * Envía un correo electrónico a un destinatario especificado.
     * 
     * Configura la conexión SMTP con los servidores de Gmail, autentica utilizando
     * las credenciales del sistema, construye el mensaje con el asunto y cuerpo proporcionados,
     * y lo envía al destinatario. Si ocurre un error durante el envío, se captura
     * y se muestra un mensaje de error en la consola.
     * 
     * @param destinatario Dirección de correo electrónico del destinatario
     * @param asunto Línea de asunto del correo electrónico
     * @param cuerpo Contenido o cuerpo del correo electrónico
     */
    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) {

        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");

        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MI_CORREO, MI_CLAVE_APP);
            }
        });

        try {
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(MI_CORREO));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            Transport.send(mensaje);
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
