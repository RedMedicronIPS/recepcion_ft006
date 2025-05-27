package org.example.recepcion_correos.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RegistroFT006")
public class RegistroFT006 {
    private String lineaNegocio;
    private String establecimiento;
    private String idEstablecimiento;
    private String codigoSIMEV;
    private String calificacionRiesgo;
    private String entidadCalificadora;
    private String otraCalificadora;
    private String claseCuenta;
    private String tipoMoneda;
    private String idCuenta;
    private String nombreCuenta;
    private long saldoExtracto;
    private long saldoLibros;
    private long sobregiro;
    private long rendimientos;
    private long gravamen;
    private String estado;
    private String fechaMedida;
    private long valorMedida;
    private long inversionReservas;
    private String asuntoCorreo;
    private String remitenteCorreo;
    private String fechaCorreo;
    private String contenidoCorreo;

    // Getters y Setters
    @XmlElement
    public String getLineaNegocio() { return lineaNegocio; }
    public void setLineaNegocio(String lineaNegocio) { this.lineaNegocio = lineaNegocio; }

    @XmlElement
    public String getEstablecimiento() { return establecimiento; }
    public void setEstablecimiento(String establecimiento) { this.establecimiento = establecimiento; }

    @XmlElement
    public String getIdEstablecimiento() { return idEstablecimiento; }
    public void setIdEstablecimiento(String idEstablecimiento) { this.idEstablecimiento = idEstablecimiento; }

    @XmlElement
    public String getCodigoSIMEV() { return codigoSIMEV; }
    public void setCodigoSIMEV(String codigoSIMEV) { this.codigoSIMEV = codigoSIMEV; }

    @XmlElement
    public String getCalificacionRiesgo() { return calificacionRiesgo; }
    public void setCalificacionRiesgo(String calificacionRiesgo) { this.calificacionRiesgo = calificacionRiesgo; }

    @XmlElement
    public String getEntidadCalificadora() { return entidadCalificadora; }
    public void setEntidadCalificadora(String entidadCalificadora) { this.entidadCalificadora = entidadCalificadora; }

    @XmlElement
    public String getOtraCalificadora() { return otraCalificadora; }
    public void setOtraCalificadora(String otraCalificadora) { this.otraCalificadora = otraCalificadora; }

    @XmlElement
    public String getClaseCuenta() { return claseCuenta; }
    public void setClaseCuenta(String claseCuenta) { this.claseCuenta = claseCuenta; }

    @XmlElement
    public String getTipoMoneda() { return tipoMoneda; }
    public void setTipoMoneda(String tipoMoneda) { this.tipoMoneda = tipoMoneda; }

    @XmlElement
    public String getIdCuenta() { return idCuenta; }
    public void setIdCuenta(String idCuenta) { this.idCuenta = idCuenta; }

    @XmlElement
    public String getNombreCuenta() { return nombreCuenta; }
    public void setNombreCuenta(String nombreCuenta) { this.nombreCuenta = nombreCuenta; }

    @XmlElement
    public long getSaldoExtracto() { return saldoExtracto; }
    public void setSaldoExtracto(long saldoExtracto) { this.saldoExtracto = saldoExtracto; }

    @XmlElement
    public long getSaldoLibros() { return saldoLibros; }
    public void setSaldoLibros(long saldoLibros) { this.saldoLibros = saldoLibros; }

    @XmlElement
    public long getSobregiro() { return sobregiro; }
    public void setSobregiro(long sobregiro) { this.sobregiro = sobregiro; }

    @XmlElement
    public long getRendimientos() { return rendimientos; }
    public void setRendimientos(long rendimientos) { this.rendimientos = rendimientos; }

    @XmlElement
    public long getGravamen() { return gravamen; }
    public void setGravamen(long gravamen) { this.gravamen = gravamen; }

    @XmlElement
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @XmlElement
    public String getFechaMedida() { return fechaMedida; }
    public void setFechaMedida(String fechaMedida) { this.fechaMedida = fechaMedida; }

    @XmlElement
    public long getValorMedida() { return valorMedida; }
    public void setValorMedida(long valorMedida) { this.valorMedida = valorMedida; }

    @XmlElement
    public long getInversionReservas() { return inversionReservas; }
    public void setInversionReservas(long inversionReservas) { this.inversionReservas = inversionReservas; }

    @XmlElement
    public String getAsuntoCorreo() { return asuntoCorreo; }
    public void setAsuntoCorreo(String asuntoCorreo) { this.asuntoCorreo = asuntoCorreo; }

    @XmlElement
    public String getRemitenteCorreo() { return remitenteCorreo; }
    public void setRemitenteCorreo(String remitenteCorreo) { this.remitenteCorreo = remitenteCorreo; }

    @XmlElement
    public String getFechaCorreo() { return fechaCorreo; }
    public void setFechaCorreo(String fechaCorreo) { this.fechaCorreo = fechaCorreo; }

    @XmlElement
    public String getContenidoCorreo() { return contenidoCorreo; }
    public void setContenidoCorreo(String contenidoCorreo) { this.contenidoCorreo = contenidoCorreo; }
}