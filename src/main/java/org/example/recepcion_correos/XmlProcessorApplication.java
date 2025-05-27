package org.example.recepcion_correos;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.example.recepcion_correos.model.FacturaElectronica;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;

@SpringBootApplication
public class XmlProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmlProcessorApplication.class, args);
    }

    public FacturaElectronica processXmlFile(String filePath) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filePath));
            XPath xpath = XPathFactory.newInstance().newXPath();

            FacturaElectronica factura = new FacturaElectronica();
            factura.setUuid(xpath.evaluate("/*[local-name()='Invoice']/*[local-name()='UUID']", doc));
            factura.setNumeroFactura(xpath.evaluate("/*[local-name()='Invoice']/*[local-name()='ID']", doc));
            factura.setFechaEmision(xpath.evaluate("/*[local-name()='Invoice']/*[local-name()='IssueDate']", doc));
            factura.setEmisor(xpath.evaluate("/*[local-name()='Invoice']/*[local-name()='AccountingSupplierParty']/*[local-name()='Party']/*[local-name()='PartyName']/*[local-name()='Name']", doc));
            factura.setReceptor(xpath.evaluate("/*[local-name()='Invoice']/*[local-name()='AccountingCustomerParty']/*[local-name()='Party']/*[local-name()='PartyName']/*[local-name()='Name']", doc));
            factura.setValorTotal(xpath.evaluate("/*[local-name()='Invoice']/*[local-name()='LegalMonetaryTotal']/*[local-name()='PayableAmount']", doc));
            factura.setCufe(xpath.evaluate("/*[local-name()='Invoice']/*[local-name()='UUID']", doc));
            factura.setXmlPath(filePath);
            factura.setXmlContent(Files.readString(Path.of(filePath)));
            return factura;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}