package com.itsight.signbox.service;

public interface EmailService {

    void enviarMensajeCredenciales(String asunto , String receptor , String contenido);

}
