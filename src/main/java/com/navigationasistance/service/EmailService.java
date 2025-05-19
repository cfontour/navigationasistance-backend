package com.navigationasistance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreo(String destino, String asunto, String cuerpo) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(destino);
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            mensaje.setFrom("tuemail@gmail.com"); // opcional si querés forzar el remitente
            mailSender.send(mensaje);
        } catch (Exception e) {
            System.err.println("❌ Error al enviar email a " + destino + ": " + e.getMessage());
        }
    }
}
