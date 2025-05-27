//package com.example.xmlprocessor.controller;

//public class RegistroController {
//}
package org.example.recepcion_correos.controller;

import java.util.List;

import org.example.recepcion_correos.EmailProcessor;
import org.example.recepcion_correos.model.RegistroFT006;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmailProcessor emailProcessor;

    @GetMapping("/")
    public String getAllRegistros(Model model) {
        String sql = "SELECT * FROM registro_ft006";
        List<RegistroFT006> registros = jdbcTemplate.query(sql, (rs, rowNum) -> {
            RegistroFT006 registro = new RegistroFT006();
            registro.setLineaNegocio(rs.getString("linea_negocio"));
            registro.setEstablecimiento(rs.getString("establecimiento"));
            registro.setIdEstablecimiento(rs.getString("id_establecimiento"));
            registro.setSaldoExtracto(rs.getLong("saldo_extracto"));
            registro.setAsuntoCorreo(rs.getString("asunto_correo"));
            registro.setRemitenteCorreo(rs.getString("remitente_correo"));
            registro.setFechaCorreo(rs.getString("fecha_correo"));
            registro.setContenidoCorreo(rs.getString("contenido_correo"));
            // Configura otros campos según sea necesario
            return registro;
        });
        model.addAttribute("registros", registros);
        return "index";
    }

    @GetMapping("/filter")
    public String filterRegistros(
            @RequestParam(required = false) String lineaNegocio,
            @RequestParam(required = false) String establecimiento,
            @RequestParam(required = false) Long saldoExtracto,
            Model model) {
        StringBuilder sql = new StringBuilder("SELECT * FROM registro_ft006 WHERE 1=1");
        if (lineaNegocio != null && !lineaNegocio.isEmpty()) {
            sql.append(" AND linea_negocio = ?");
        }
        if (establecimiento != null && !establecimiento.isEmpty()) {
            sql.append(" AND establecimiento = ?");
        }
        if (saldoExtracto != null) {
            sql.append(" AND saldo_extracto = ?");
        }

        List<RegistroFT006> registros = jdbcTemplate.query(sql.toString(), (rs, rowNum) -> {
            RegistroFT006 registro = new RegistroFT006();
            registro.setLineaNegocio(rs.getString("linea_negocio"));
            registro.setEstablecimiento(rs.getString("establecimiento"));
            registro.setIdEstablecimiento(rs.getString("id_establecimiento"));
            registro.setSaldoExtracto(rs.getLong("saldo_extracto"));
            registro.setAsuntoCorreo(rs.getString("asunto_correo"));
            registro.setRemitenteCorreo(rs.getString("remitente_correo"));
            registro.setFechaCorreo(rs.getString("fecha_correo"));
            registro.setContenidoCorreo(rs.getString("contenido_correo"));
            // Configura otros campos según sea necesario
            return registro;
        }, lineaNegocio, establecimiento, saldoExtracto);

        model.addAttribute("registros", registros);
        return "index";
    }

    @PostMapping("/procesar-correos")
    public String procesarCorreosGet() {
        emailProcessor.processEmails();
        return "redirect:/";
    }
}