package org.example.recepcion_correos.controller;

import java.util.List;
import org.example.recepcion_correos.EmailProcessor;
import org.example.recepcion_correos.model.FacturaElectronica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FacturaController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmailProcessor emailProcessor;

    @GetMapping("/")
    public String getAllFacturas(Model model) {
        String sql = "SELECT * FROM factura_electronica";
        List<FacturaElectronica> facturas = jdbcTemplate.query(sql, (rs, rowNum) -> {
            FacturaElectronica factura = new FacturaElectronica();
            factura.setId(rs.getLong("id"));
            factura.setUuid(rs.getString("uuid"));
            factura.setNumeroFactura(rs.getString("numero_factura"));
            factura.setFechaEmision(rs.getString("fecha_emision"));
            factura.setEmisor(rs.getString("emisor"));
            factura.setReceptor(rs.getString("receptor"));
            factura.setValorTotal(rs.getString("valor_total"));
            factura.setCufe(rs.getString("cufe"));
            factura.setPdfPath(rs.getString("pdf_path"));
            factura.setXmlPath(rs.getString("xml_path"));
            factura.setXmlContent(rs.getString("xml_content"));
            factura.setAsuntoCorreo(rs.getString("asunto_correo"));
            factura.setRemitenteCorreo(rs.getString("remitente_correo"));
            factura.setFechaCorreo(rs.getString("fecha_correo"));
            factura.setContenidoCorreo(rs.getString("contenido_correo"));
            return factura;
        });
        model.addAttribute("facturas", facturas);
        return "index";
    }

    @PostMapping("/procesar-correos")
    public String procesarCorreosGet() {
        emailProcessor.processEmails();
        return "redirect:/";
    }
}