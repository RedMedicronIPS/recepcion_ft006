package org.example.recepcion_correos.model;

public class FacturaElectronica {
    private Long id;
    private String uuid;
    private String numeroFactura;
    private String fechaEmision;
    private String emisor;
    private String receptor;
    private String valorTotal;
    private String cufe;
    private String pdfPath;
    private String xmlPath;
    private String xmlContent;
    private String asuntoCorreo;
    private String remitenteCorreo;
    private String fechaCorreo;
    private String contenidoCorreo;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
    public String getNumeroFactura() { return numeroFactura; }
    public void setNumeroFactura(String numeroFactura) { this.numeroFactura = numeroFactura; }
    public String getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(String fechaEmision) { this.fechaEmision = fechaEmision; }
    public String getEmisor() { return emisor; }
    public void setEmisor(String emisor) { this.emisor = emisor; }
    public String getReceptor() { return receptor; }
    public void setReceptor(String receptor) { this.receptor = receptor; }
    public String getValorTotal() { return valorTotal; }
    public void setValorTotal(String valorTotal) { this.valorTotal = valorTotal; }
    public String getCufe() { return cufe; }
    public void setCufe(String cufe) { this.cufe = cufe; }
    public String getPdfPath() { return pdfPath; }
    public void setPdfPath(String pdfPath) { this.pdfPath = pdfPath; }
    public String getXmlPath() { return xmlPath; }
    public void setXmlPath(String xmlPath) { this.xmlPath = xmlPath; }
    public String getXmlContent() { return xmlContent; }
    public void setXmlContent(String xmlContent) { this.xmlContent = xmlContent; }
    public String getAsuntoCorreo() { return asuntoCorreo; }
    public void setAsuntoCorreo(String asuntoCorreo) { this.asuntoCorreo = asuntoCorreo; }
    public String getRemitenteCorreo() { return remitenteCorreo; }
    public void setRemitenteCorreo(String remitenteCorreo) { this.remitenteCorreo = remitenteCorreo; }
    public String getFechaCorreo() { return fechaCorreo; }
    public void setFechaCorreo(String fechaCorreo) { this.fechaCorreo = fechaCorreo; }
    public String getContenidoCorreo() { return contenidoCorreo; }
    public void setContenidoCorreo(String contenidoCorreo) { this.contenidoCorreo = contenidoCorreo; }
}