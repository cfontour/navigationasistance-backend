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
        int enviado = emailService.enviarHtml(email);
        if (enviado == 1) {
            return "Correo enviado correctamente";
        }
        return "No se pudo enviar el correo";
    }
}
