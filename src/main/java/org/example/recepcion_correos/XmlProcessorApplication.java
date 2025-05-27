package org.example.recepcion_correos;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.example.recepcion_correos.model.RegistroFT006;
import org.example.recepcion_correos.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XmlProcessorApplication {

    @Autowired
    private RegistroService registroService;

    public static void main(String[] args) {
        SpringApplication.run(XmlProcessorApplication.class, args);
    }

    public RegistroFT006 processXmlFile(String filePath) {
        try {
            // Leer el XML como texto
            String xmlContent = new String(java.nio.file.Files.readAllBytes(new File(filePath).toPath()));

            // Elimina cualquier carácter antes del primer '<'
            xmlContent = xmlContent.substring(xmlContent.indexOf('<'));

            // Elimina el carácter '|' si está después del prolog
            xmlContent = xmlContent.replaceFirst("\\?>\\|", "?>");

            // Corrige etiquetas mal cerradas (solo para casos comunes)
            xmlContent = xmlContent.replaceAll("</\\s+", "</");

            // Si el XML tiene un nodo raíz FT006, extrae el contenido de RegistroFT006
            if (xmlContent.contains("<FT006>")) {
                int start = xmlContent.indexOf("<RegistroFT006>");
                int end = xmlContent.indexOf("</RegistroFT006>") + "</RegistroFT006>".length();
                xmlContent = xmlContent.substring(start, end);
            }

            // Escribe el XML limpio a un archivo temporal
            File cleanFile = new File("clean_temp.xml");
            java.nio.file.Files.write(cleanFile.toPath(), xmlContent.getBytes());

            JAXBContext jaxbContext = JAXBContext.newInstance(RegistroFT006.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            RegistroFT006 registro = (RegistroFT006) unmarshaller.unmarshal(cleanFile);

            if (registro.getLineaNegocio() != null && registro.getEstablecimiento() != null) {
                return registro;
            } else {
                System.err.println("El XML no contiene los campos requeridos.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}