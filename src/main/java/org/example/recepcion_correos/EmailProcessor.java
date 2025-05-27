package org.example.recepcion_correos;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import org.example.recepcion_correos.model.RegistroFT006;
import org.example.recepcion_correos.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.mail.BodyPart;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.MimeMultipart;

@Component
public class EmailProcessor {

    @Autowired
    private RegistroService registroService;

    @Autowired
    private XmlProcessorApplication xmlProcessorApplication;

    public void processEmails() {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", "imap.gmail.com");
        props.put("mail.imaps.port", "993");
        props.put("mail.imaps.ssl.enable", "true");

        try {
            Session session = Session.getDefaultInstance(props);
            Store store = session.getStore("imaps");
            store.connect("tics@redmedicronips.com.co", "ljne hotf urtu qstm");

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message[] messages = inbox.getMessages();

            for (Message message : messages) {
                if (message.getContent() instanceof MimeMultipart) {
                    MimeMultipart multipart = (MimeMultipart) message.getContent();
                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);
                        if (bodyPart.getFileName() != null && bodyPart.getFileName().endsWith(".xml")) {
                            File file = new File("temp.xml");
                            try (FileOutputStream fos = new FileOutputStream(file)) {
                                bodyPart.getInputStream().transferTo(fos);
                            }
                            // Lee el contenido para depuración
                            String xmlContent = new String(java.nio.file.Files.readAllBytes(file.toPath()));
                            System.out.println("Contenido XML recibido:\n" + xmlContent);
                            // Procesa el XML y agrega datos del correo
                            RegistroFT006 registro = xmlProcessorApplication.processXmlFile(file.getAbsolutePath());
                            if (registro != null) {
                                registro.setAsuntoCorreo(message.getSubject());
                                registro.setRemitenteCorreo(message.getFrom()[0].toString());
                                registro.setFechaCorreo(message.getSentDate() != null ? message.getSentDate().toString() : "");
                                registro.setContenidoCorreo(getTextFromMessage(message));
                                registroService.guardarRegistro(registro);
                            }
                        }
                    }
                }
            }
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Utilidad para extraer el texto del mensaje
    private String getTextFromMessage(Message message) {
        try {
            if (message.isMimeType("text/plain")) {
                return message.getContent().toString();
            } else if (message.isMimeType("multipart/*")) {
                MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < mimeMultipart.getCount(); i++) {
                    BodyPart bodyPart = mimeMultipart.getBodyPart(i);
                    if (bodyPart.isMimeType("text/plain")) {
                        result.append(bodyPart.getContent());
                    }
                }
                return result.toString();
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }
}