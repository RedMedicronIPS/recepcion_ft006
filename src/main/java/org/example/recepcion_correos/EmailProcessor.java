package org.example.recepcion_correos;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.example.recepcion_correos.model.FacturaElectronica;
import org.example.recepcion_correos.service.FacturaService;
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
    private FacturaService facturaService;

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
                        String fileName = bodyPart.getFileName();
                        if (fileName != null && fileName.endsWith(".zip")) {
                            Path pdfPath = null;
                            Path xmlPath = null;
                            // Procesar ZIP
                            try (ZipInputStream zis = new ZipInputStream(bodyPart.getInputStream())) {
                                ZipEntry entry;
                                while ((entry = zis.getNextEntry()) != null) {
                                    if (entry.getName().endsWith(".xml")) {
                                        xmlPath = Files.createTempFile("correo_", ".xml");
                                        Files.copy(zis, xmlPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                                    } else if (entry.getName().endsWith(".pdf")) {
                                        Path pdfDir = Path.of("c:/Proyectos/recepcion_correos/pdfs/");
                                        if (!Files.exists(pdfDir)) {
                                            Files.createDirectories(pdfDir);
                                        }
                                        String pdfFileName = System.currentTimeMillis() + "_" + entry.getName();
                                        pdfPath = pdfDir.resolve(pdfFileName);
                                        Files.copy(zis, pdfPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                                    }
                                    zis.closeEntry();
                                }
                            }
                            // Procesar la factura solo si hay XML
                            if (xmlPath != null) {
                                FacturaElectronica factura = xmlProcessorApplication.processXmlFile(xmlPath.toString());
                                if (factura != null) {
                                    factura.setAsuntoCorreo(message.getSubject());
                                    factura.setRemitenteCorreo(message.getFrom()[0].toString());
                                    factura.setFechaCorreo(message.getSentDate() != null ? message.getSentDate().toString() : "");
                                    factura.setContenidoCorreo(getTextFromMessage(message));
                                    factura.setXmlPath(xmlPath.toString());
                                    if (pdfPath != null) {
                                        factura.setPdfPath(pdfPath.toString());
                                    }
                                    facturaService.guardarFactura(factura);
                                }
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