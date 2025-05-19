package com.navigationasistance.controler;

import com.navigationasistance.modelo.Email;
import com.navigationasistance.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailControler {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar")
    public String enviarCorreo(@RequestBody Email email) {

        // LOGS que verás en el panel de Render
        System.out.println("📧 Ingresó al método /email/enviar");
        System.out.println("📧 Destinatario: " + email.getDestinatario());
        System.out.println("📧 Asunto: " + email.getAsunto());

        int enviado = emailService.enviarHtml(email);
        if (enviado == 1) {
            System.out.println("✅ Correo enviado correctamente");
            return "Correo enviado correctamente";
        }
        return "No se pudo enviar el correo";
    }
}
