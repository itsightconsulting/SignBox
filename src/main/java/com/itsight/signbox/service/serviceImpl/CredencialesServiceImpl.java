package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.domain.Persona;
import com.itsight.signbox.service.CredencialesService;
import com.itsight.signbox.service.EmailService;
import com.itsight.util.MailsContent;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.itsight.util.Parseador.getDecodeBase64;

@Service
public class CredencialesServiceImpl implements CredencialesService {

    @Value("${twilio.ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("${twilio.AUTH_TOKEN}")
    private String AUTH_TOKEN;

    EmailService emailService;

    public CredencialesServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }


    @Override
    public void enviarCredencialesxCorreo(Persona persona) {

        String contrasenia = getDecodeBase64(persona.getHashContrasenia());
        StringBuilder sb = MailsContent.contenidoRecordatorioCredenciales(persona.getCorreo(), contrasenia);
        emailService.enviarMensajeCredenciales("Credenciales CMMAC Piura", "josejch11@gmail.com", sb.toString());

    }


    @Override
    public void enviarCredencialesxSMS(Persona persona) {

        String contrasenia = getDecodeBase64(persona.getHashContrasenia());
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("+51930994926"),
                new PhoneNumber("+12055831198"),
                "Sus credenciales de CMMAC Piura son las siguientes \n   USUARIO : " + persona.getCorreo() + "\n PASSWORD :"  +contrasenia)
                .create();
    }
}
