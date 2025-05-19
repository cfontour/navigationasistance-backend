package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Email;
import com.navigationasistance.modeloDAO.EmailDAO;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public EmailDAO toDAO(Email email) {
        EmailDAO dao = new EmailDAO();
        dao.setDestinatario(email.getDestinatario());
        dao.setAsunto(email.getAsunto());
        dao.setContenidoHtml(email.getContenidoHtml());
        return dao;
    }

    public Email toModel(EmailDAO dao) {
        Email email = new Email();
        email.setDestinatario(dao.getDestinatario());
        email.setAsunto(dao.getAsunto());
        email.setContenidoHtml(dao.getContenidoHtml());
        return email;
    }
}
