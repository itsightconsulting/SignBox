package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.generic.EmailGeneric;
import com.itsight.signbox.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl extends EmailGeneric implements EmailService {


    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${spring.mail.username}")
    private String emitterMail;

    private JavaMailSender emailSender;

   // private BandejaTemporalRepository bandejaTemporalRepository;

    public static final Logger LOGGER = LogManager.getLogger(EmailServiceImpl.class);

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void enviarMensajeCredenciales(String asunto, String receptor, String contenido) {
        MimeMessagePreparator preparator = mimeMessagePreparator(asunto, receptor, contenido);
        try {
            emailSender.send(preparator);
        } catch (MailException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }
}
