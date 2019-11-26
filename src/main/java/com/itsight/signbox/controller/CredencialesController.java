package com.itsight.signbox.controller;

import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.Persona;
import com.itsight.signbox.domain.Usuarios;
import com.itsight.signbox.domain.dto.PersonaDTO;
import com.itsight.signbox.domain.pojo.PersonaPOJO;
import com.itsight.signbox.service.*;
import com.itsight.util.Enums;
import com.itsight.util.Enums.TipoEnvio;
import com.itsight.util.Utilitarios;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.itsight.util.Enums.TipoEnvio.SMS_EMAIL;
import static com.itsight.util.Enums.TipoEnvio.getTipoEnvioFromInt;
import static com.itsight.util.Parseador.getDecodeBase64;
import static com.itsight.util.Parseador.getEncodeBase64;
import static com.itsight.util.Utilitarios.getRandomString;

@RestController
@RequestMapping("/portalAdmin/seguridad/credenciales")
public class CredencialesController {

    private PersonaProcedureInvoker personaProcedureInvoker;
    private PersonaService personaService;

    @Value("${spring.profiles.active}")
    private String profile;


    @Value("${doc.contrasenia.base}")
    private String variableContrasenia;

    @Value("${doc.longitud}")
    private String variableLongitud;

    private CredencialesService credencialesService;

    @Autowired
    public CredencialesController(PersonaProcedureInvoker personaProcedureInvoker,
                                  PersonaService personaService, CredencialesService credencialesService) {
        this.personaProcedureInvoker = personaProcedureInvoker;
        this.personaService = personaService;
        this.credencialesService = credencialesService;
    }

    @GetMapping(value = "/gestion")
    public ModelAndView principal(Model model) {
        return new ModelAndView(ViewConstant.MAIN_CREDENCIALES);
    }


    @GetMapping("")
    public ResponseEntity<List<PersonaPOJO>> listarTodo(
            @RequestParam String dni,
            @RequestParam Integer tipo,
            @RequestParam Integer offset, @RequestParam Integer limit){

        return new ResponseEntity<List<PersonaPOJO>>(
                personaProcedureInvoker.ListarPersonas(
                        limit,offset, dni, tipo), HttpStatus.OK);
    }

    @PutMapping("actualizar/{id}")
    public Persona actualizar(@PathVariable Integer id, @ModelAttribute @Valid PersonaDTO parametro) throws NotFoundValidationException {

        Persona qPersona = personaService.findOne(id);

        qPersona.setTelefono(parametro.getTelefono());
        qPersona.setCorreo(parametro.getCorreo());

        return personaService.update(qPersona);
    }

    @GetMapping("ObtenerPorId/{id}")
    public Persona ObtenerPorId(@PathVariable Integer id) throws NotFoundValidationException {

        Persona qPersona = personaService.findOne(id);
        qPersona.setGuid("");
        qPersona.setHashContrasenia("");
        qPersona.setFolderBase("");

        return qPersona;
    }

    @GetMapping("enviar-credenciales/")
    public String enviarCredenciales(@RequestParam Integer id ,  @RequestParam Integer tipo ) throws NotFoundValidationException {

        Persona persona = personaService.findOne(id);

        switch (getTipoEnvioFromInt(tipo))
        {
            case SMS_EMAIL
                    :
                if (!Utilitarios.isNullOrWhitespace(persona.getCorreo())) {
                    credencialesService.enviarCredencialesxCorreo(persona);
                }
                if (!Utilitarios.isNullOrWhitespace(persona.getTelefono())) {
                    credencialesService.enviarCredencialesxSMS(persona);
                }
                ;
                break;

            case EMAIL:

                if (!Utilitarios.isNullOrWhitespace(persona.getCorreo())) {
                    credencialesService.enviarCredencialesxCorreo(persona);
                }
            ; break;
            case SMS:

                if (!Utilitarios.isNullOrWhitespace(persona.getTelefono())){
                }
                credencialesService.enviarCredencialesxSMS(persona);
                ;break;
            default:
                throw new IllegalStateException("Valor inesperado: " + tipo);
        }
        return "true";
    }

    @PutMapping("reseteo-credenciales/{id}")
    public Map<String, String> resetearCredenciales (@PathVariable Integer id) throws NotFoundValidationException {

        Persona qPersona = personaService.findOne(id);
        String contrasenia = "";
        String hashContrasenia = "";
        if(profile.equals("development")){
            contrasenia = variableContrasenia ;
        }else if(profile.equals("production")){
            contrasenia = getRandomString(Integer.parseInt(variableLongitud));
        }
        hashContrasenia = new BCryptPasswordEncoder().encode(contrasenia);
        qPersona.setHashContrasenia(hashContrasenia);
        personaService.update(qPersona);

        return Collections.singletonMap("password", contrasenia);
    }
}
