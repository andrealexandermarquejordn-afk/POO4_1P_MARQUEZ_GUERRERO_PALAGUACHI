import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class CorreoElectronico {

    private static final String MI_CORREO = "mundialprincipal2026@gmail.com";
    private static final String MI_CLAVE_APP = "aajp msxo ahld pstz";

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
