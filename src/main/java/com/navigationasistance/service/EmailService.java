package com.navigationasistance.service;

import com.navigationasistance.modelo.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.*;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public int enviarHtml(Email email) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            helper.setTo(email.getDestinatario());
            helper.setSubject(email.getAsunto());
            helper.setText(email.getContenidoHtml(), true);
            //helper.setFrom("cargusproductions@gmail.com"); // opcional

            mailSender.send(mensaje);
            return 1;
        } catch (MessagingException e) {
            System.err.println("❌ Error al enviar email: " + e.getMessage());
            return 0;
        }
    }
}
