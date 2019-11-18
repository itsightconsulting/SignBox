package com.itsight.signbox.service;

import com.itsight.signbox.domain.Persona;

public interface CredencialesService {

    void enviarCredencialesxCorreo(Persona persona);

    void enviarCredencialesxSMS(Persona persona);


}
