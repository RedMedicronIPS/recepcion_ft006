package org.example.recepcion_correos.service;

import org.example.recepcion_correos.model.FacturaElectronica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void guardarFactura(FacturaElectronica factura) {
        String sql = "INSERT INTO factura_electronica (uuid, numero_factura, fecha_emision, emisor, receptor, valor_total, cufe, pdf_path, xml_path, xml_content, asunto_correo, remitente_correo, fecha_correo, contenido_correo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            factura.getUuid(),
            factura.getNumeroFactura(),
            factura.getFechaEmision(),
            factura.getEmisor(),
            factura.getReceptor(),
            factura.getValorTotal(),
            factura.getCufe(),
            factura.getPdfPath(),
            factura.getXmlPath(),
            factura.getXmlContent(),
            factura.getAsuntoCorreo(),
            factura.getRemitenteCorreo(),
            factura.getFechaCorreo(),
            factura.getContenidoCorreo()
        );
    }
}