package com.itsight.signbox.controller;

import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.Persona;
import com.itsight.signbox.domain.Usuarios;
import com.itsight.signbox.domain.dto.PersonaDTO;
import com.itsight.signbox.domain.pojo.PersonaPOJO;
import com.itsight.signbox.service.*;
import com.itsight.util.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequestMapping("/seguridad/credenciales")
public class CredencialesController {

    private PersonaProcedureInvoker personaProcedureInvoker;
    private PersonaService personaService;



    @Value("${doc.ambiente}")
    private String VariableAmbiente;

    @Value("${doc.contrasenia.base}")
    private String VariableContrasenia;

    @Value("${doc.longitud}")
    private String VariableLongitud;

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


    @GetMapping("listarTodo")
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
        credencialesService.enviarCredencialesxCorreo(persona);

        return "true";
    }
/*
    @GetMapping("ObtenerPorId/{id}")
    public String ResetearCredenciales (@PathVariable Integer id) throws NotFoundValidationException {

        Persona qPersona = personaService.findOne(id);

        String ambiente = VariableAmbiente;
        String contrasenia = "";
        String hashContrasenia = "";
        if (ambiente == ViewConstant.AMBIENTE_DESARROLLO)
        {
            contrasenia = VariableContrasenia;
            hashContrasenia = SignBoxContract.Security.SHAMager.ManagerSHA256(contrasenia);
        }else{
            contrasenia = VariableLongitud;
            hashContrasenia = SignBoxContract.Security.SHAMager.ManagerSHA256(contrasenia);
        }

        personaService.ActualizarContrasenia(id, hashContrasenia, contrasenia);
        personaService.ActualizarFlagCambio(id);

        return contrasenia;
    }

    @GetMapping("ObtenerPorId/{id}")
    public void EnviarCredenciales (@PathVariable Integer id, Integer tipo) throws NotFoundValidationException {

        Persona qPersona = personaService.findOne(id);

        EmailManager email = new  EmailManager() ;
        SMSManager sms  = new SMSManager();

        var rutaCorreo = AppSettings.Get<string>("Correo.Contrasena");
        var cuerpoCorreo = File.ReadAllText(rutaCorreo);

        switch (tipo)
        {
            case (int)TipoEnvio.Ambos:
            {
                if (!string.IsNullOrWhiteSpace(persona.Correo))
                    email.EnviarCorreoCredenciales(cuerpoCorreo, id, string.Empty);

                if (!string.IsNullOrWhiteSpace(persona.Telefono))
                    sms.EnviarSMSCredenciales(id, id.ToString());
            }; break;
            case (int)TipoEnvio.Correo:
            {
                if (!string.IsNullOrWhiteSpace(persona.Correo))
                    email.EnviarCorreoCredenciales(cuerpoCorreo, id, string.Empty);

            }; break;
            case (int)TipoEnvio.SMS:
            {
                if (!string.IsNullOrWhiteSpace(persona.Telefono))
                    sms.EnviarSMSCredenciales(id, id.ToString());
            };break;
        }
    }*/
}
