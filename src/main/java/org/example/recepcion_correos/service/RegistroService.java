package org.example.recepcion_correos.service;

import org.example.recepcion_correos.model.RegistroFT006;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void guardarRegistro(RegistroFT006 registro) {
        String sql = "INSERT INTO registro_ft006 (linea_negocio, establecimiento, id_establecimiento, codigo_simev, " +
                "calificacion_riesgo, entidad_calificadora, otra_calificadora, clase_cuenta, tipo_moneda, id_cuenta, " +
                "nombre_cuenta, saldo_extracto, saldo_libros, sobregiro, rendimientos, gravamen, estado, fecha_medida, " +
                "valor_medida, inversion_reservas, asunto_correo, remitente_correo, fecha_correo) " + 
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                registro.getLineaNegocio(),
                registro.getEstablecimiento(),
                registro.getIdEstablecimiento(),
                registro.getCodigoSIMEV(),
                registro.getCalificacionRiesgo(),
                registro.getEntidadCalificadora(),
                registro.getOtraCalificadora(),
                registro.getClaseCuenta(),
                registro.getTipoMoneda(),
                registro.getIdCuenta(),
                registro.getNombreCuenta(),
                registro.getSaldoExtracto(),
                registro.getSaldoLibros(),
                registro.getSobregiro(),
                registro.getRendimientos(),
                registro.getGravamen(),
                registro.getEstado(),
                registro.getFechaMedida(),
                registro.getValorMedida(),
                registro.getInversionReservas(),
                registro.getAsuntoCorreo(),
                registro.getRemitenteCorreo(),
                registro.getFechaCorreo()
        );
    }
}