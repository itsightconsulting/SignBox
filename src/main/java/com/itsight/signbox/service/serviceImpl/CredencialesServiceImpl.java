package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.Persona;
import com.itsight.signbox.service.CredencialesService;
import com.itsight.signbox.service.EmailService;
import com.itsight.util.MailsContent;
import org.springframework.stereotype.Service;

@Service
public class CredencialesServiceImpl implements CredencialesService {

    EmailService emailService;

    public CredencialesServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void enviarCredencialesxCorreo(Persona persona) {
        StringBuilder sb = MailsContent.contenidoRecordatorioCredenciales(persona.getCorreo(), persona.getHashContrasenia());
        emailService.enviarMensajeCredenciales("Bienvenido a la familia", "josejch11@gmail.com", sb.toString());

    }
}
