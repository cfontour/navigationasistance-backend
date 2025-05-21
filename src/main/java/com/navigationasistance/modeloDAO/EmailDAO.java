package com.navigationasistance.modeloDAO;

import java.util.List;

public class EmailDAO {

    private List<String> destinatario; // ✅
    private String asunto;
    private String contenidoHtml;

    // Getters y setters
    public List<String> getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(List<String> destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getContenidoHtml() { return contenidoHtml; }
    public void setContenidoHtml(String contenidoHtml) { this.contenidoHtml = contenidoHtml; }
}
